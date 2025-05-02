package org.instantai.api.controller;

import org.instantai.api.service.NotebookService;
import org.instantai.api.service.ResourcesService;
import org.instantai.api.service.impl.ResourcesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private NotebookService notebookService;

    @GetMapping("/nodes/metrics")
    public List<ResourcesServiceImpl.NodeUsage> getNodeMetrics() {
        return resourcesService.getNodeMetrics();
    }

    @GetMapping("/notebooks/count")
    public Mono<Long> getApiRouteCount() {
        return notebookService.getTotalNumber();
    }

}
