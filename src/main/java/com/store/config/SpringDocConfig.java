package com.store.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Store apis", version = "1.0", description = "Documentation of store APIs v1.0"))
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi userApis() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/user/**")
                .addOpenApiCustomizer(getOpenApiCustomizer())
                .build();
    }

    @Bean
    public GroupedOpenApi storeApis() {
        return GroupedOpenApi.builder()
                .group("store")
                .pathsToMatch("/store/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productApis() {
        return GroupedOpenApi.builder()
                .group("products")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cartApis() {
        return GroupedOpenApi.builder()
                .group("cart")
                .pathsToMatch("/cart/**")
                .build();
    }

    public OpenApiCustomizer getOpenApiCustomizer() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem
                .readOperations().stream()).forEach(operation -> {
                    operation.addParametersItem(new Parameter().name("Authorization").in("header")
                        .schema(new StringSchema().example("token")).required(true));
                });

    }

}