package org.instantai.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("workspace_permissions")
@Builder
public class WorkspacePermission {

    @Column("workspace_name")
    private String workspaceName;

    @Column("username")
    private String username;

    @Column("role")
    private String role; // 'view' or 'edit'
}
