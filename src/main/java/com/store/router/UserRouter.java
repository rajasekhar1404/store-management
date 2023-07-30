package com.store.router;

import com.store.dto.UserDTO;
import com.store.handler.UserHandler;
import com.store.modal.CreateUserRequest;
import com.store.service.UserService;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/user/register",
                            beanClass = UserService.class,
                            beanMethod = "register",
                            produces = MediaType.APPLICATION_JSON_VALUE,
                            method = RequestMethod.POST,
                            operation = @Operation(
                                    operationId = "register",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserDTO.class)))
                                    },
                                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = CreateUserRequest.class)))
                            )
                    ),
                    @RouterOperation(
                            path = "/user/zip/{zip}",
                            beanClass = UserService.class,
                            produces = MediaType.APPLICATION_JSON_VALUE,
                            method = RequestMethod.GET,
                            beanMethod = "findUsersByZip",
                            operation = @Operation(
                                    operationId = "findUsersByZip",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserDTO.class))),
                                            @ApiResponse(responseCode = "404", description = "Not found"),
                                            @ApiResponse(responseCode = "500", description = "Internal server error")
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "zip")
                                    }
                            )
                    ),
                    @RouterOperation(
                            path = "/user/country/{country}",
                            beanClass = UserService.class,
                            beanMethod = "findByCountry",
                            method = RequestMethod.GET,
                            produces = MediaType.APPLICATION_JSON_VALUE,
                            operation = @Operation(
                                    operationId = "findByCountry",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserDTO.class)))
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "country")
                                    }
                            )
                    )
            }
    )
    public RouterFunction<ServerResponse> userRoutes(final UserHandler userHandler) {
        return RouterFunctions.route()
                .GET("/", userHandler::message)
                .POST("/user/register", userHandler::registerUser)
                .GET("/user/zip/{zip}", userHandler::findByZip)
                .GET("/user/id/{id}", userHandler::findById)
                .GET("/user/email/{email}", userHandler::findByEmail)
                .GET("/user/country/{country}", userHandler::findByCountry)
                .PUT("/user/update/{id}", userHandler::updateUser)
                .DELETE("/user/{id}", userHandler::deleteById)
                .build();
    }

}
