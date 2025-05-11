package org.instantai.api.service.impl;

import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.exception.MissingFieldException;
import org.instantai.api.model.CreateOrUpdateApiRouteRequest;
import org.instantai.api.service.ApiRouteService;
import org.instantai.api.service.NotebookService;
import org.instantai.api.service.UserService;
import org.instantai.api.service.WorkspaceService;
import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.NotebookSpec;
import org.kubeflow.v1.notebookspec.Template;
import org.kubeflow.v1.notebookspec.template.Spec;
import org.kubeflow.v1.notebookspec.template.spec.Containers;
import org.kubeflow.v1.notebookspec.template.spec.containers.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class NotebookServiceImpl implements NotebookService {
    @Autowired
    private UserService userService;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private KubernetesClient kubernetesClient;

    @Autowired
    private ApiRouteService apiRouteService;

    private List<String> validateRequiredFields(Containers containers) {
        List<String> missingFields = new ArrayList<>();

        if (containers.getName() == null) {
            missingFields.add("name");
        }
        if (containers.getImage() == null) {
            missingFields.add("image");
        }
        if (containers.getResources() == null) {
            missingFields.add("resources");
        } else if (containers.getResources().getLimits() == null) {
            missingFields.add("resources.limits");
        }

        return missingFields;
    }

    private Notebook buildNotebookSpec(String namespace, String name, String path, Containers containers) {
        Notebook notebook = new Notebook();

        notebook.setMetadata(new ObjectMetaBuilder()
                .withName(name)
                .withNamespace(namespace)
                .build());

        Env envVar = new Env();
        envVar.setName("NB_PREFIX");
        envVar.setValue(path);

        containers.setEnv(List.of(envVar));
        containers.setName("main");

        Spec podSpec = new Spec();
        podSpec.setContainers(List.of(containers));

        Template template = new Template();
        template.setSpec(podSpec);

        NotebookSpec spec = new NotebookSpec();
        spec.setTemplate(template);
        notebook.setSpec(spec);

        return notebook;
    }

    @Override
    public Mono<Void> createOrUpdateNotebook(String namespace, Containers containers) {
        List<String> missingFields = validateRequiredFields(containers);
        if (!missingFields.isEmpty()) {
            return Mono.error(new MissingFieldException("Missing required fields", missingFields));
        }

        return userService.getCurrentUsername()
                .doOnNext(username -> log.info("Creating notebook for user: {}", username))
                .flatMap(username ->
                        workspaceService.isAdminOrEditor(namespace, username)
                                .doOnNext(permitted -> log.info("Permission check for [{} in {}]: {}", username, namespace, permitted))
                                .flatMap(hasPermission -> {
                                    if (!hasPermission) {
                                        return Mono.error(new AccessDeniedException("No permission to create/update notebook"));
                                    }

                                    String name = containers.getName();
                                    String path = String.format("/notebooks/%s/%s", namespace, name);
                                    Notebook notebook = buildNotebookSpec(namespace, name, path, containers);

                                    MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient = kubernetesClient.resources(Notebook.class);
                                    Resource<Notebook> notebookResource = notebookClient.inNamespace(namespace).withName(name);

                                    return Mono.fromCallable(notebookResource::get)
                                            .subscribeOn(Schedulers.boundedElastic())
                                            .flatMap(existing -> {
                                                log.info("Notebook exists, replacing: {}", existing.getMetadata().getName());
                                                return Mono.fromCallable(() -> notebookClient.resource(notebook).create())
                                                        .subscribeOn(Schedulers.boundedElastic())
                                                        .flatMap(createdNotebook -> {
                                                            return Mono.fromCallable(() -> notebookResource.replace(notebook)).subscribeOn(Schedulers.boundedElastic())
                                                                    .then();
                                                        });
                                            })
                                            .switchIfEmpty(Mono.defer(() -> {
                                                log.info("Notebook [{}] not found (null), creating...", name);
                                                return Mono.fromCallable(() -> notebookClient.resource(notebook).create())
                                                        .subscribeOn(Schedulers.boundedElastic())
                                                        .flatMap(createdNotebook -> {
                                                            CreateOrUpdateApiRouteRequest route = new CreateOrUpdateApiRouteRequest();
                                                            route.setPath(path + "/**");
                                                            route.setUri(String.format("http://%s.%s.svc", name, namespace));
                                                            log.info("Creating route for notebook: {}", route);
                                                            return apiRouteService.createApiRoute(route).then();
                                                        });
                                            }));

                                })
                );
    }


    @Override
    public List<Notebook> listNotebooks(String namespace) {
        MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                kubernetesClient.resources(Notebook.class);
        return notebookClient.inNamespace(namespace).resources()
                .map(Resource::item)
                .toList();
    }

    @Override
    public Mono<Void> deleteNotebook(String namespace, String name) {
        return userService.getCurrentUsername().flatMap(currentUsername -> workspaceService.isAdminOrEditor(namespace, currentUsername)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return Mono.error(new AccessDeniedException("No permission"));
                    }
                    MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                            kubernetesClient.resources(Notebook.class);
                    notebookClient.inNamespace(namespace).withName(name).delete();

                    // 删除 API 路由
                    return apiRouteService.deleteApiRoute(String.format("/notebooks/%s/%s/**", namespace, name))
                            .then();
                })
        );
    }

    @Override
    public Mono<Void> stopNotebook(String namespace, String name) {
        return userService.getCurrentUsername()
                .flatMap(currentUsername -> workspaceService.isAdminOrEditor(namespace, currentUsername)
                        .flatMap(hasPermission -> {
                            log.info("has permission");
                            if (!hasPermission) {
                                return Mono.error(new AccessDeniedException("No permission to stop notebook"));
                            }

                            MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                                    kubernetesClient.resources(Notebook.class);
                            Resource<Notebook> notebookResource = notebookClient.inNamespace(namespace).withName(name);
                            Notebook notebook = notebookResource.get();
                            if (notebook == null) {
                                return Mono.error(new NoSuchElementException("notebook not found."));
                            }

                            Instant now = Instant.now();
                            String timeStr = now.atZone(ZoneId.of("UTC"))
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));

                            notebook.getMetadata().setAnnotations(
                                    Optional.ofNullable(notebook.getMetadata().getAnnotations()).orElseGet(HashMap::new)
                            );
                            notebook.getMetadata().getAnnotations().put("kubeflow-resource-stopped", timeStr);
                            log.info("notebook annotations: {}", notebook.getMetadata().getAnnotations());
                            notebookClient.inNamespace(namespace).withName(name).patch(notebook);

                            return Mono.empty();
                        }));
    }

    @Override
    public Mono<Void> startNotebook(String namespace, String name) {
        return userService.getCurrentUsername()
                .flatMap(currentUsername -> workspaceService.isAdminOrEditor(namespace, currentUsername)
                        .flatMap(hasPermission -> {
                            if (!hasPermission) {
                                return Mono.error(new AccessDeniedException("No permission to start notebook"));
                            }

                            MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                                    kubernetesClient.resources(Notebook.class);
                            Resource<Notebook> notebookResource = notebookClient.inNamespace(namespace).withName(name);
                            Notebook notebook = notebookResource.get();
                            if (notebook == null) {
                                return Mono.error(new NoSuchElementException("notebook not found."));
                            }

                            notebook.getMetadata().getAnnotations().remove("kubeflow-resource-stopped");
                            notebookClient.inNamespace(namespace).withName(name).patch(notebook);

                            return Mono.empty();
                        }));
    }

    @Override
    public Mono<Long> getTotalNumber() {
        return apiRouteService.getApiRouteCount();
    }
}
