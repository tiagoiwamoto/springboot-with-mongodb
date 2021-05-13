package br.com.tiagoiwamoto.api.config;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 28/04/2021 | 21:35
 */

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Blog API")
                        .description("API of a simple Blog.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://localhost")))
                .externalDocs(new ExternalDocumentation()
                        .description("BlogApi Wiki Documentation")
                        .url("https://localhost:4200/docs"));
    }
}
