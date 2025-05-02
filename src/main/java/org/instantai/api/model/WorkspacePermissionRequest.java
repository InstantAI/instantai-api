package org.instantai.api.model;

import lombok.Data;

@Data
public class WorkspacePermissionRequest {
    private String username;
    private String role; // 'view' or 'edit'
}
