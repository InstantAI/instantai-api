package org.instantai.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.instantai.api.repository.WorkspacePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;



@Component
@Slf4j
public class NotebookAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Autowired
    private WorkspacePermissionRepository workspacePermissionRepository;

    @Autowired
    private ReactiveOAuth2AuthorizedClientService clientService;

    private Mono<Boolean> hasAdminRole(OAuth2AuthenticationToken auth) {
        return clientService
                .loadAuthorizedClient(auth.getAuthorizedClientRegistrationId(), auth.getName())
                .map(client -> client.getAccessToken().getTokenValue())
                .flatMap(accessToken -> {
                    // Decode JWT
                    DecodedJWT jwt = JWT.decode(accessToken);
                    Map<String, Object> resourceAccess = jwt.getClaim("resource_access").asMap();
                    if (resourceAccess == null) return Mono.just(false);
                    Map<String, Object> instantai = (Map<String, Object>) resourceAccess.get(clientId);
                    if (instantai == null) return Mono.just(false);
                    List<String> roles = (List<String>) instantai.get("roles");
                    return Mono.just(roles != null && roles.contains("admin"));
                });
    }

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
        String path = context.getExchange().getRequest().getPath().value();
        // 判断是否是 /notebooks/{workspaceId}/ 开头的请求
        if (path.startsWith("/notebooks/")) {
            String[] segments = path.split("/");
            if (segments.length >= 3) {
                String workspaceId = segments[2]; // /notebooks/workspace-1/

                return authentication
                        .filter(Authentication::isAuthenticated)
                        .flatMap(auth -> {
                            if (auth instanceof OAuth2AuthenticationToken oauth2Auth) {
                                // First, check if the user has admin role
                                return hasAdminRole(oauth2Auth)
                                        .flatMap(hasAdmin -> {
                                            if (hasAdmin) {
                                                // If the user has admin role, authorize them directly
                                                return Mono.just(true);
                                            }
                                            // If not an admin, check if they are authorized for the specific workspace
                                            Map<String, Object> attributes = oauth2Auth.getPrincipal().getAttributes();
                                            String username = (String) attributes.get("preferred_username");
                                            return isAuthorized(username, workspaceId);
                                        });
                            }
                            return Mono.just(false); // If not OAuth2AuthenticationToken
                        })
                        .map(AuthorizationDecision::new)
                        .defaultIfEmpty(new AuthorizationDecision(false));
            }
        }
        return Mono.just(new AuthorizationDecision(true));
    }



    private boolean hasRole(Map<String, Object> attributes, String clientId, String role) {
        try {
            Map<String, Object> resourceAccess = (Map<String, Object>) attributes.get("resource_access");
            if (resourceAccess == null) return false;

            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get(clientId);
            if (clientRoles == null) return false;

            List<String> roles = (List<String>) clientRoles.get("roles");
            return roles != null && roles.contains(role);
        } catch (ClassCastException | NullPointerException e) {
            log.warn("Failed to extract role [{}] from resource_access for client [{}]", role, clientId, e);
            return false;
        }
    }

    private Mono<Boolean> isAuthorized(String username, String workspaceId) {
        return workspacePermissionRepository
                .findByWorkspaceNameAndUsername(workspaceId, username)
                .map(permission -> {
                    // 你可以根据访问类型决定是否只允许 'edit' 或允许 'view' 也通过
                    return permission.getRole().equals("view") || permission.getRole().equals("edit");
                })
                .defaultIfEmpty(false);
    }
}
