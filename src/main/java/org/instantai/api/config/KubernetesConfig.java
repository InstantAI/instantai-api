package org.instantai.api.config;

import io.fabric8.kubernetes.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KubernetesConfig {

    @Bean
    public KubernetesClient kubernetesClient() {
        Config config = new ConfigBuilder()
                .withNamespace(null) // Use the default namespace
                .build();
        return new KubernetesClientBuilder().withConfig(config).build();
    }
}
