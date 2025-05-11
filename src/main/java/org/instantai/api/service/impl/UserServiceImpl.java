package org.instantai.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.instantai.api.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Override
    public Mono<String> getCurrentUsername() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(this::extractUsername);
    }



    @Override
    public Mono<Boolean> hasAdminRole() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(auth -> auth instanceof JwtAuthenticationToken)
                .cast(JwtAuthenticationToken.class)
                .map(token -> {
                    Map<String, Object> resourceAccess = token.getToken().getClaim("resource_access");
                    if (resourceAccess == null) return false;
                    Map<String, Object> instantai = (Map<String, Object>) resourceAccess.get(clientId);
                    if (instantai == null) return false;
                    List<String> roles = (List<String>) instantai.get("roles");
                    return roles != null && roles.contains("admin");
                })
                .defaultIfEmpty(false);
    }

    private Mono<String> extractUsername(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            Object principal = oauth2Token.getPrincipal();
            if (principal instanceof DefaultOidcUser oidcUser) {
                return Mono.justOrEmpty(oidcUser.getPreferredUsername()); // from ID Token "preferred_username"
            } else {
                return Mono.justOrEmpty(oauth2Token.getName()); // fallback to subject
            }
        } else if (authentication instanceof JwtAuthenticationToken jwtToken) {
            return Mono.justOrEmpty(jwtToken.getToken().getClaimAsString("preferred_username"));
        } else {
            return Mono.justOrEmpty(authentication.getName());
        }
    }
}
