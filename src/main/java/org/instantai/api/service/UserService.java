package org.instantai.api.service;

import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> getCurrentUsername();
}
