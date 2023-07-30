package com.store.router;

import com.store.handler.ProductHandler;
import com.store.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/product/store/{id}",
                            method = RequestMethod.GET,
                            beanMethod = "findByStoreId",
                            beanClass = ProductHandler.class,
                            operation = @Operation(
                                    operationId = "findByStoreId",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id")
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/product/name/{name}",
                            method = RequestMethod.GET,
                            beanMethod = "findByName",
                            beanClass = ProductHandler.class,
                            operation = @Operation(
                                    operationId = "findByName",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "name")
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/product/id/{id}",
                            method = RequestMethod.GET,
                            beanMethod = "findById",
                            beanClass = ProductHandler.class,
                            operation = @Operation(
                                    operationId = "findById",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> productRoutes(final ProductHandler productHandler) {
        return RouterFunctions.route()
                    .POST("/product", productHandler::createProduct)
                    .GET("/product/id/{id}", productHandler::findById)
                    .GET("/product/name/{name}", productHandler::findByName)
                    .GET("/product/price", productHandler::findByPriceRange)
                    .GET("/product/store/{id}", productHandler::findByStoreId)
                    .PUT("/product", productHandler::updateProduct)
                    .DELETE("/product/{id}", productHandler::deleteProduct)
                .build();
    }

}
