package br.com.tiagoiwamoto.api.samples.springbootwithmongodb.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
    # Tiago Henrique Iwamoto
    # tiago.iwamoto@gmail.com
    # 14/01/2021 - 15:05
*/

@Configuration
public class SwaggerConfig {

    //url default de acesso é: http://localhost:8082/swagger-ui/index.html

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springboot-with-mongodb-public")
                .packagesToScan("br.com.tiagoiwamoto.api.samples.springbootwithmongodb.controller")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API to manager users")
                        .description("This api manager the users.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://localhost")))
                .externalDocs(new ExternalDocumentation()
                        .description("Users Wiki Documentation")
                        .url("https://localhost:4200/docs"));
    }
}
