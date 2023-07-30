package com.store.router;

import com.store.handler.CartHandler;
import com.store.modal.AddProductToCartRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CartRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/cart/{userId}",
                            method = RequestMethod.GET,
                            beanClass = CartHandler.class,
                            beanMethod = "findByUserId",
                            operation = @Operation(
                                    operationId = "findByUserId",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "userId")
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/cart/item/add",
                            method = RequestMethod.PUT,
                            beanMethod = "addItemToCart",
                            beanClass = CartHandler.class,
                            operation = @Operation(
                                    operationId = "addItemToCart",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Successful operation"),
                                            @ApiResponse(responseCode = "404", description = "Not found"),
                                            @ApiResponse(responseCode = "500", description = "Internal server error")
                                    },
                                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = AddProductToCartRequest.class)))
                            )
                    ),
                    @RouterOperation(
                            path = "/cart/clear/{userId}",
                            method = RequestMethod.DELETE,
                            beanClass = CartHandler.class,
                            beanMethod = "clearCart",
                            operation = @Operation(
                                    operationId = "clearCart",
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "userId")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> cartRoutes(final CartHandler cartHandler) {
        return RouterFunctions.route()
                .GET("/cart/{userId}", cartHandler::findByUserId)
                .PUT("/cart/item/add", cartHandler::addItemToCart)
                .DELETE("/cart/clear/{userId}", cartHandler::clearCart)
                .build();
    }

}
