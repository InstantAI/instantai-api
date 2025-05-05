package org.instantai.api.repository;

import org.instantai.api.model.WorkspacePermission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WorkspacePermissionRepository extends ReactiveCrudRepository<WorkspacePermission, String> {
    Mono<WorkspacePermission> findByWorkspaceNameAndUsername(String workspaceName, String username);
    Flux<WorkspacePermission> findByWorkspaceName(String workspaceName);
    Flux<WorkspacePermission> findAllByUsername(String username);
    Mono<Void> deleteByWorkspaceNameAndUsername(String workspaceName, String username);
}
