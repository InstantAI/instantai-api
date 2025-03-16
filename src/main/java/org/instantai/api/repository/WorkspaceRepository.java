package org.instantai.api.repository;

import org.instantai.api.model.Workspace;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, String>  {
}
