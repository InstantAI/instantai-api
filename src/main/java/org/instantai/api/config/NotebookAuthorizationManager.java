package org.instantai.api.config;

import lombok.extern.slf4j.Slf4j;
import org.instantai.api.repository.WorkspacePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class NotebookAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private WorkspacePermissionRepository workspacePermissionRepository;

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
                                Map<String, Object> attributes = oauth2Auth.getPrincipal().getAttributes();
                                String username = (String) attributes.get("preferred_username");
                                return isAuthorized(username, workspaceId);
                            }
                            return Mono.just(false);
                        })
                        .map(AuthorizationDecision::new)
                        .defaultIfEmpty(new AuthorizationDecision(false));
            }
        }
        return Mono.just(new AuthorizationDecision(true));
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

//    private Mono<Boolean> isAuthorized(String username, String workspaceId) {
//        log.debug("username: {}, workspace: {}", username, workspaceId);
//        // 这里可以访问数据库或缓存，例如：
//        // return permissionService.hasAccess(username, workspaceId);
//        if(username.equals("admin")) {
//            log.info("user is admin, pass");
//            return Mono.just(/* your condition here */ true);
//        }
//        return Mono.just(/* your condition here */ false);
//    }
}
