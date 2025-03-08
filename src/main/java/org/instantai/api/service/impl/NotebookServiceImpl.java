package org.instantai.api.service.impl;
import io.fabric8.kubernetes.client.KubernetesClient;

import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.exception.MissingFieldException;
import org.instantai.api.model.CreateOrUpdateApiRouteRequest;
import org.instantai.api.service.ApiRouteService;
import org.instantai.api.service.NotebookService;
import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.NotebookSpec;
import org.kubeflow.v1.notebookspec.Template;
import org.kubeflow.v1.notebookspec.template.Spec;
import org.kubeflow.v1.notebookspec.template.spec.Containers;
import org.kubeflow.v1.notebookspec.template.spec.containers.Env;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NotebookServiceImpl implements NotebookService {
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

    @Override
    public void createOrUpdateNotebook(String namespace,Containers containers) {
        List<String> missingFields = validateRequiredFields(containers);
        if (!missingFields.isEmpty()) {
            throw new MissingFieldException("Missing required fields", missingFields);
        }
        String name = containers.getName();
        Notebook notebook = new Notebook();
        notebook.setMetadata(new io.fabric8.kubernetes.api.model.ObjectMetaBuilder()
                .withName(name)
                .withNamespace(namespace)
                .build());

        NotebookSpec spec = new NotebookSpec();
        Template template = new Template();
        Spec podSpec = new Spec();
        // 添加环境变量 NB_PREFIX
        Env envVar = new Env();
        envVar.setName("NB_PREFIX");
        String path = String.format("/notebooks/%s/%s", namespace, name);
        envVar.setValue(path);
        // 创建并设置容器
        containers.setEnv(List.of(envVar));
        containers.setName("main");
        podSpec.setContainers(List.of(containers));
        template.setSpec(podSpec);
        spec.setTemplate(template);
        notebook.setSpec(spec);

        MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                kubernetesClient.resources(Notebook.class);
        Resource<Notebook> notebookResource = notebookClient.inNamespace(namespace).withName(name);
        if (notebookResource.get() != null) {
            log.info("name: {}", notebookResource.get().getMetadata().getName());
            // 如果 Notebook 已存在，则替换
            notebookResource.replace(notebook);
        } else {
            // 如果 Notebook 不存在，则创建
            notebookClient.resource(notebook).create();
            CreateOrUpdateApiRouteRequest route = new CreateOrUpdateApiRouteRequest();
            route.setPath(path + "/**");
            route.setUri(String.format("http://%s.%s.svc", name, namespace));
            apiRouteService.createApiRoute(route).subscribe();
        }

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
    public void deleteNotebook(String namespace, String name) {
        MixedOperation<Notebook, ?, Resource<Notebook>> notebookClient =
                kubernetesClient.resources(Notebook.class);
        notebookClient.inNamespace(namespace).withName(name).delete();
        String path = String.format("/notebooks/%s/%s/**", namespace, name);
        apiRouteService.deleteApiRoute(path).subscribe();
    }
}
