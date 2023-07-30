package com.store.service;

import com.store.dto.CartDTO;
import com.store.modal.AddProductToCartRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface CartService {

    Mono<CartDTO> findByUserId(String userId);

    Mono<CartDTO> addItemToCart(Mono<AddProductToCartRequest> addProductToCartRequestMono);

    Mono<Void> clearCart(String userId);
}
