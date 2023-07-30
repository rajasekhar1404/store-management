package com.store.mapper;

import com.store.dto.CartDTO;
import com.store.entity.Cart;

public class CartMapper {

    public static CartDTO mapCartToCartDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .products(cart.getProducts())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

}
