package org.instantai.api.controller;

import jakarta.validation.Valid;
import org.instantai.api.model.Workspace;
import org.instantai.api.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("")
    public Mono<ResponseEntity<Flux<Workspace>>> getWorkspace() {
        return Mono.just(
                ResponseEntity.ok()
                        .body(workspaceService.findWorkspaces())
        ).onErrorResume(e ->
                Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Workspace>> createWorkspace(@Valid @RequestBody Workspace workspace) {
        return workspaceService.createWorkspace(workspace)
                .map(created -> ResponseEntity
                        .created(URI.create("/api/workspaces/" + created.getName()))
                        .body(created))
                // 如果捕获到 IllegalArgumentException，则返回 409（Conflict）及异常信息
                .onErrorResume(IllegalArgumentException.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).build())
                )
                // 其他异常返回 500（Internal Server Error）
                .onErrorResume(e ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .build())
                );
    }

}
