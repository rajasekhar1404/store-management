package com.store.handler;

import com.store.dto.StoreDTO;
import com.store.modal.CreateStoreRequest;
import com.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreHandler {

    private final StoreService storeService;

    public Mono<ServerResponse> registerStore(ServerRequest request) {
        return ServerResponse.ok().body(
                    storeService.register(request.bodyToMono(CreateStoreRequest.class)
                ), StoreDTO.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(
          storeService.findAll(), StoreDTO.class
        );
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ServerResponse.ok().body(
                    storeService.findById(request.pathVariable("id")
                ), StoreDTO.class);
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        return ServerResponse.ok().body(
                storeService.findByName(request.pathVariable("name")), StoreDTO.class
        );
    }

    public Mono<ServerResponse> findByOwnerId(ServerRequest request) {
        return ServerResponse.ok().body(
          storeService.findByOwnerId(request.pathVariable("id")), StoreDTO.class
        );
    }

    public Mono<ServerResponse> findByCountry(ServerRequest request) {
        return ServerResponse.ok().body(
                storeService.findByCountry(request.pathVariable("country")), StoreDTO.class
        );
    }

    public Mono<ServerResponse> findByState(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> findByCity(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> findByZipCode(ServerRequest request) {
        return null;
    }

}
