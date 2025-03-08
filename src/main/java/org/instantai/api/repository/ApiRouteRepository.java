package org.instantai.api.repository;

import org.instantai.api.model.ApiRoute;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ApiRouteRepository extends ReactiveCrudRepository<ApiRoute, Long> {
    Mono<ApiRoute> findByPath(String path);
}
