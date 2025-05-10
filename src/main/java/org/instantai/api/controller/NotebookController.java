package org.instantai.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.instantai.api.service.NotebookService;
import org.kubeflow.v1.Notebook;
import org.kubeflow.v1.notebookspec.template.spec.Containers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
@Slf4j
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @PostMapping("/{namespace}")
    public Mono<Void> createNotebook(@PathVariable String namespace, @RequestBody Containers containers) {
        return notebookService.createOrUpdateNotebook(namespace, containers);
    }

    @GetMapping("/{namespace}")
    public List<Notebook> getNotebooks(@PathVariable String namespace) {
        return notebookService.listNotebooks(namespace);
    }

    @DeleteMapping("/{namespace}")
    public Mono<Void> deleteNotebook(@PathVariable String namespace, @Param("name") String name) {
        return notebookService.deleteNotebook(namespace, name);
    }

    @PutMapping("/{namespace}/{name}/status")
    public Mono<Void> setNotebookStatus(@PathVariable String namespace, @PathVariable String name,
                                  @Param("action") String action) {
        return switch (action) {
            case "stop" -> notebookService.stopNotebook(namespace, name);
            case "start" -> notebookService.startNotebook(namespace, name);
            default -> {
                log.warn("invalid action");
                yield Mono.error(new IllegalArgumentException("Invalid action: " + action));
            }
        };
    }
}
