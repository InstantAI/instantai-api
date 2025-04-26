package org.instantai.api.service.impl;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.model.Workspace;
import org.instantai.api.repository.WorkspaceRepository;
import org.instantai.api.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public Flux<Workspace> findWorkspaces() {
        return workspaceRepository.findAll();
    }


    private Mono<Void> createK8sNamespace(Workspace workspace) {
        return Mono.fromRunnable(() -> {
            try (KubernetesClient client = new KubernetesClientBuilder().build()) {
                String namespaceName = workspace.getName();

                boolean exists = client.namespaces().withName(namespaceName).get() != null;
                if (!exists) {
                    log.info("Creating Kubernetes namespace: {}", namespaceName);

                    Namespace ns = new NamespaceBuilder()
                            .withNewMetadata()
                            .withName(namespaceName)
                            .addToLabels(K8sConstants.OWNER_LABEL_KEY, K8sConstants.OWNER_LABEL_VALUE)
                            .endMetadata()
                            .build();

                    client.namespaces().create(ns);
                    log.info("Namespace '{}' created successfully.", namespaceName);
                }

                // 无论是新建还是已有，下面创建/更新 ResourceQuota
                ResourceQuota rq = new ResourceQuotaBuilder()
                        .withNewMetadata()
                        .withName("workspace-quota")
                        .withNamespace(namespaceName)
                        .endMetadata()
                        .withNewSpec()
                        .addToHard("limits.cpu", Quantity.parse(workspace.getCpuLimit()))
                        .addToHard("limits.memory", Quantity.parse(workspace.getMemoryLimit()))
                        .addToHard("requests.nvidia.com/gpu", Quantity.parse(workspace.getGpuLimit() != null ? workspace.getGpuLimit() + "" : "0"))
                        .endSpec()
                        .build();

                client.resourceQuotas().inNamespace(namespaceName).createOrReplace(rq);
                log.info("ResourceQuota for namespace '{}' created or updated successfully.", namespaceName);

            } catch (Exception e) {
                log.error("Failed to create namespace or ResourceQuota", e);
                throw new RuntimeException("Kubernetes命名空间或资源配额创建失败");
            }
        });
    }

    private Mono<Void> updateK8sNamespaceQuota(Workspace workspace) {
        return Mono.fromRunnable(() -> {
            try (KubernetesClient client = new KubernetesClientBuilder().build()) {
                String namespaceName = workspace.getName();

                var existingNs = client.namespaces().withName(namespaceName).get();
                if (existingNs == null) {
                    log.warn("Namespace '{}' does not exist, creating...", namespaceName);
                    createK8sNamespace(workspace).block(); // 注意这里同步block一下
                    return;
                }

                log.info("Updating ResourceQuota for namespace: {}", namespaceName);

                ResourceQuota rq = new ResourceQuotaBuilder()
                        .withNewMetadata()
                        .withName("workspace-quota")
                        .withNamespace(namespaceName)
                        .endMetadata()
                        .withNewSpec()
                        .addToHard("limits.cpu", Quantity.parse(workspace.getCpuLimit()))
                        .addToHard("limits.memory", Quantity.parse(workspace.getMemoryLimit()))
                        .addToHard("requests.nvidia.com/gpu", Quantity.parse(workspace.getGpuLimit() != null ? workspace.getGpuLimit() + "" : "0"))
                        .endSpec()
                        .build();

                client.resourceQuotas().inNamespace(namespaceName).createOrReplace(rq);
                log.info("ResourceQuota for namespace '{}' updated successfully.", namespaceName);

            } catch (Exception e) {
                log.error("Failed to update ResourceQuota", e);
                throw new RuntimeException("Kubernetes资源配额更新失败");
            }
        });
    }


    private Mono<Void> deleteK8sNamespaceIfExists(String namespaceName) {
        return Mono.fromRunnable(() -> {
            try (KubernetesClient client = new KubernetesClientBuilder().build()) {
                var namespace = client.namespaces().withName(namespaceName).get();
                if (namespace != null) {
                    log.info("Deleting Kubernetes namespace: {}", namespaceName);
                    client.namespaces().withName(namespaceName).delete();
                    log.info("Namespace '{}' deleted successfully.", namespaceName);
                } else {
                    log.info("Namespace '{}' does not exist. Skipping delete.", namespaceName);
                }
            } catch (Exception e) {
                log.error("Failed to delete Kubernetes namespace: {}", namespaceName, e);
                throw new RuntimeException("Kubernetes命名空间删除失败");
            }
        });
    }

    @Override
    public Mono<Workspace> createWorkspace(Workspace workspace) {
        log.info("Attempting to create or update workspace: {}", workspace.getName());

        return workspaceRepository.existsById(workspace.getName())
                .flatMap(exists -> {
                    if (exists) {
                        // 已存在，更新数据库并且更新 K8s 的 ResourceQuota
                        log.info("Workspace '{}' exists. Updating...", workspace.getName());
                        workspace.setNewEntry(false);
                        return workspaceRepository.save(workspace)
                                .flatMap(saved -> updateK8sNamespaceQuota(saved)
                                        .thenReturn(saved)
                                )
                                .doOnSuccess(saved -> log.info("Workspace updated successfully: {}", saved))
                                .doOnError(error -> log.error("Failed to update workspace", error));
                    } else {
                        // 不存在，正常新建
                        log.info("Workspace '{}' does not exist. Creating...", workspace.getName());
                        return workspaceRepository.save(workspace)
                                .flatMap(saved -> createK8sNamespace(saved)
                                        .thenReturn(saved)
                                )
                                .doOnSuccess(saved -> log.info("Workspace created successfully: {}", saved))
                                .doOnError(error -> log.error("Failed to create workspace", error));
                    }
                })
                .onErrorResume(error -> {
                    log.error("Error creating or updating workspace", error);
                    return Mono.error(new RuntimeException("操作失败，请稍后重试"));
                });
    }

    @Override
    public Mono<Void> deleteWorkspace(String workspaceName) {
        log.info("Attempting to delete workspace: {}", workspaceName);

        return workspaceRepository.findById(workspaceName)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Workspace不存在")))
                .flatMap(workspace ->
                        workspaceRepository.deleteById(workspaceName)
                                .then(deleteK8sNamespaceIfExists(workspaceName))
                )
                .doOnSuccess(v -> log.info("Workspace '{}' deleted successfully", workspaceName))
                .doOnError(error -> log.error("Failed to delete workspace '{}'", workspaceName, error))
                .onErrorResume(error -> {
                    if (error instanceof IllegalArgumentException) {
                        return Mono.error(error);
                    }
                    return Mono.error(new RuntimeException("删除失败，请稍后重试"));
                });
    }
}
