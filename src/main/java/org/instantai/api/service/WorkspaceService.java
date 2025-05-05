package org.instantai.api.service;

import org.instantai.api.model.Workspace;
import org.instantai.api.model.WorkspacePermission;
import org.instantai.api.model.WorkspaceWithRole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WorkspaceService {
    Flux<WorkspaceWithRole> findWorkspaces();
    Mono<Workspace> createWorkspace(Workspace workspace);
    Mono<Void> deleteWorkspace(String workspaceName);
    Mono<WorkspacePermission> addPermission(String workspaceName, String username, String role);
    Mono<Void> removePermission(String workspaceName, String username);
    Mono<WorkspacePermission> getPermission(String workspaceName, String username);
    Flux<WorkspacePermission> getPermissions(String workspaceName);
    Mono<Boolean> isAdminOrEditor(String workspaceName, String username);
}
