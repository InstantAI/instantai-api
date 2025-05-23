package org.instantai.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrUpdateApiRouteRequest {

    @NotBlank
    private String path;

    private String method;

    @NotBlank
    private String uri;
}
