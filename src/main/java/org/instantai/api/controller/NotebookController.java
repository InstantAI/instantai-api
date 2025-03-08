package org.instantai.api.controller;

import org.instantai.api.service.NotebookService;
import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.notebookspec.template.spec.Containers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @PostMapping("/{namespace}")
    public void createNotebook(@PathVariable String namespace, @RequestBody Containers containers) {
        notebookService.createOrUpdateNotebook(namespace, containers);
    }

    @GetMapping("/{namespace}")
    public List<Notebook> getNotebooks(@PathVariable String namespace) {
        return notebookService.listNotebooks(namespace);
    }

    @DeleteMapping("/{namespace}")
    public void deleteNotebook(@PathVariable String namespace, @Param("name") String name) {
        notebookService.deleteNotebook(namespace, name);
    }
}
