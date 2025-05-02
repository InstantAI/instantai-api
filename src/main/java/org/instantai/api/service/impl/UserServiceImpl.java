package org.instantai.api.service.impl;

import org.instantai.api.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Mono<String> getCurrentUsername() {
        return ReactiveSecurityContextHolder.getContext()
                .map(context -> context.getAuthentication())
                .flatMap(this::extractUsername);
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
