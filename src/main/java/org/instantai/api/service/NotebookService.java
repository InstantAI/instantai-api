package org.instantai.api.service;

import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.notebookspec.template.spec.Containers;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotebookService {
    Mono<Void> createOrUpdateNotebook(String namespace,Containers containers);
    List<Notebook> listNotebooks(String namespace);
    Mono<Void> deleteNotebook(String namespace, String name);
    Mono<Void> stopNotebook(String namespace, String name);
    Mono<Void> startNotebook(String namespace, String name);
    Mono<Long> getTotalNumber();
}
