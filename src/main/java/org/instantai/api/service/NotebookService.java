package org.instantai.api.service;

import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.notebookspec.template.spec.Containers;

import java.util.List;

public interface NotebookService {
    void createOrUpdateNotebook(String namespace,Containers containers);
    List<Notebook> listNotebooks(String namespace);
    void deleteNotebook(String namespace, String name);
    void stopNotebook(String namespace, String name);
    void startNotebook(String namespace, String name);
}
