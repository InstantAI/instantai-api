package org.instantai.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.instantai.api.model.Workspace;
import org.instantai.api.repository.WorkspaceRepository;
import org.instantai.api.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public Flux<Workspace> findWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Override
    public Mono<Workspace> createWorkspace(Workspace workspace) {
        log.info("Attempting to create workspace: {}", workspace.getName());

        return workspaceRepository.existsById(workspace.getName())
                .flatMap(exists -> {
                    if (exists) {
                        log.warn("Workspace already exists: {}", workspace.getName());
                        return Mono.error(new IllegalArgumentException("Workspace已存在"));
                    } else {
                        return workspaceRepository.save(workspace)
                                .doOnSuccess(saved -> log.info("Workspace saved successfully: {}", saved))
                                .doOnError(error -> log.error("Failed to save workspace", error));
                    }
                })
                .onErrorResume(error -> {
                    if (error instanceof IllegalArgumentException) {
                        return Mono.error(error);
                    }
                    log.error("Error creating workspace", error);
                    return Mono.error(new RuntimeException("创建失败，请稍后重试"));
                });
    }
}
