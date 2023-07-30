package com.store.router;

import com.store.handler.StoreHandler;
import com.store.service.StoreService;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StoreRouter {

    @Bean
    @RouterOperations(
        {
                @RouterOperation(
                        path = "/store/register",
                        method = RequestMethod.POST,
                        beanMethod = "register",
                        beanClass = StoreService.class
                ),
                @RouterOperation(
                        path = "/store/all",
                        method = RequestMethod.GET,
                        beanClass = StoreService.class,
                        beanMethod = "findAll"
                )
        }
    )
    public RouterFunction<ServerResponse> storeRoutes(final StoreHandler storeHandler) {
        return RouterFunctions.route()
                .POST("/store/register", storeHandler::registerStore)
                .GET("/store/id/{id}", storeHandler::findById)
                .GET("/store/all", storeHandler::findAll)
                .GET("/store/name/{name}", storeHandler::findByName)
                .GET("/store/owner/{id}", storeHandler::findByOwnerId)
                .GET("/store/country/{country}", storeHandler::findByCountry)
                .build();
    }

}
