package org.instantai.api.service;

import org.instantai.api.model.Workspace;
import org.instantai.api.model.WorkspacePermission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkspaceService {
    Flux<Workspace> findWorkspaces();
    Mono<Workspace> createWorkspace(Workspace workspace);
    Mono<Void> deleteWorkspace(String workspaceName);
    Mono<WorkspacePermission> addPermission(String workspaceName, String username, String role);
    Mono<Void> removePermission(String workspaceName, String username);
    Mono<WorkspacePermission> getPermission(String workspaceName, String username);
}
