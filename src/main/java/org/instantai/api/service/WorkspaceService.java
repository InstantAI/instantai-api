package org.instantai.api.service;

import org.instantai.api.model.Workspace;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkspaceService {
    Flux<Workspace> findWorkspaces();
    Mono<Workspace> createWorkspace(Workspace workspace);
    Mono<Void> deleteWorkspace(String workspaceName);
}
