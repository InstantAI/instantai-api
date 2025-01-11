package org.instantai.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("api_route") // To bind our model class with a database table with defined name
public class ApiRoute {
    @Id // Indicating that this field is primary key in our database table
    private Long id;

    private String path;
    private String method;
    private String uri;
}
