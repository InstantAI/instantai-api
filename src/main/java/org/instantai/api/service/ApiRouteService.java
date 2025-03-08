package org.instantai.api.service;

import org.instantai.api.model.ApiRoute;
import org.instantai.api.model.CreateOrUpdateApiRouteRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiRouteService {
    Flux<ApiRoute> findApiRoutes();

    Mono<ApiRoute> findApiRoute(Long id);

    Mono<ApiRoute> findApiRoute(String path);

    Mono<Void> createApiRoute(CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);

    Mono<Void> updateApiRoute(Long id, CreateOrUpdateApiRouteRequest createOrUpdateApiRouteRequest);

    Mono<Void> deleteApiRoute(Long id);
    Mono<Void> deleteApiRoute(String path);
}
