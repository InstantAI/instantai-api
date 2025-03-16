package org.instantai.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("workspaces")
@Builder
public class Workspace implements Persistable<String> {
    @Id
    @Column("name")
    @Pattern(regexp = "^[a-z0-9]([-a-z0-9]*[a-z0-9])?(\\.[a-z0-9]([-a-z0-9]*[a-z0-9])?)*$",
            message = "必须符合Kubernetes命名空间命名规则")
    private String name;

    @Column("cpu_limit")
    private String cpuLimit;

    @Column("memory_limit")
    private String memoryLimit;

    @Column("gpu_limit")
    private Integer gpuLimit;

    @Transient
    @Builder.Default
    @JsonIgnore
    private boolean isNewEntry = true;

    @Override
    public String getId() {
        return this.name;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return isNewEntry;
    }
}
