package org.instantai.api.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.model.Workspace;
import org.instantai.api.model.WorkspacePermission;
import org.instantai.api.model.WorkspacePermissionRequest;
import org.instantai.api.service.UserService;
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
@Slf4j
public class WorkspaceController {
    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private UserService userService;

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
    public Mono<ResponseEntity<Workspace>> createOrUpdateWorkspace(@Valid @RequestBody Workspace workspace) {
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

    @DeleteMapping("/{name}")
    public Mono<ResponseEntity<Void>> deleteWorkspace(@PathVariable String name) {
        return workspaceService.deleteWorkspace(name)
                .then(Mono.just(buildVoidResponseEntity(HttpStatus.NO_CONTENT))) // 204 No Content
                .onErrorResume(IllegalArgumentException.class, e ->
                        Mono.just(buildVoidResponseEntity(HttpStatus.NOT_FOUND)) // 404 Not Found
                )
                .onErrorResume(e -> {
                    log.error("Unexpected error while deleting workspace: {}", name, e);
                    return Mono.just(buildVoidResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)); // 500 Internal Server Error
                });
    }

    private ResponseEntity<Void> buildVoidResponseEntity(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

    @GetMapping("/{name}/permissions/me")
    public Mono<WorkspacePermission> getPermissions(@PathVariable String name) {
        return userService.getCurrentUsername()
                .flatMap(username -> workspaceService.getPermission(name, username));
    }

    @PostMapping("/{name}/permissions")
    public Mono<WorkspacePermission> addPermission(@PathVariable String name,
                                                   @RequestBody WorkspacePermissionRequest request) {
        return workspaceService.addPermission(name, request.getUsername(), request.getRole());
    }

    @DeleteMapping("/{name}/permissions/{username}")
    public Mono<Void> removePermission(@PathVariable String workspace,
                                       @PathVariable String username) {
        return workspaceService.removePermission(workspace, username);
    }

}
