package com.store.handler;

import com.store.dto.CartDTO;
import com.store.modal.AddProductToCartRequest;
import com.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CartHandler {

    private final CartService cartService;

    public Mono<ServerResponse> findByUserId(ServerRequest request) {
        return ServerResponse.ok().body(
                cartService.findByUserId(request.pathVariable("userId")), CartDTO.class
        );
    }

    public Mono<ServerResponse> addItemToCart(ServerRequest request) {

        Mono<AddProductToCartRequest> addProductToCartRequestMono = Mono.just(new AddProductToCartRequest("8a9a0094-fafa-4fd4-ac73-90025dddcd0d", "510f7fb6-efbb-4a70-ae34-80cb0df6d9ed", 2.0));
//                request.bodyToMono(AddProductToCartRequest.class).switchIfEmpty(Mono.error(new RuntimeException("NOTHING FOUND")));

        return ServerResponse.ok().body(
          cartService.addItemToCart(addProductToCartRequestMono), CartDTO.class
        );
    }

    public Mono<ServerResponse> clearCart(ServerRequest request) {
        return ServerResponse.ok().body(
                cartService.clearCart(request.pathVariable("userId")), Void.class
        );
    }

}
