package com.store.service.impl;

import com.store.dto.CartDTO;
import com.store.entity.Cart;
import com.store.entity.Product;
import com.store.helper.ProductHelper;
import com.store.mapper.CartMapper;
import com.store.modal.AddProductToCartRequest;
import com.store.modal.Quantity;
import com.store.repo.CartRepository;
import com.store.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ProductHelper implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Mono<CartDTO> findByUserId(String userId) {
        return findOrCreateNewCartByUserId(userId).map(CartMapper::mapCartToCartDTO);
    }

    @Override
    public Mono<CartDTO> addItemToCart(Mono<AddProductToCartRequest> addProductToCartRequestMono) {

        // find the cart by user id
        Mono<Mono<Cart>> userCartMono = addProductToCartRequestMono.map(addPCR -> findOrCreateNewCartByUserId(addPCR.getUserId()));

        // find the product by product id
        Mono<Product> productMono = addProductToCartRequestMono.flatMap(addPCR -> super.findById(addPCR.getProductId()));

        // update the cart
        Mono<Cart> updatedCart = addProductToCartRequestMono
                .flatMap(addPCR ->
                        productMono.flatMap(product ->
                                userCartMono.flatMap(userCart ->
                                        userCart.map(cart -> updateCart(addPCR.getQuantity(), product, cart))
                                )
                        )
                );

        // save the cart
        return updatedCart.flatMap(cartRepository::save).map(CartMapper::mapCartToCartDTO);
    }

    @Override
    public Mono<Void> clearCart(String userId) {
        return cartRepository.findByUserId(userId).doOnNext(cart -> {
            cart.setProducts(new ArrayList<>());
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }).then(Mono.empty());
    }

    private Cart updateCart(Double quantity, Product product, Cart cart) {
        // get if product already exists in the cart
        Optional<Product> optionalProduct = cart.getProducts().stream()
                .filter(eachProduct -> eachProduct.getId().equals(product.getId()))
                .findFirst();

        // if exists update the quantity and put it back in the cart with updated price else add new
        if (optionalProduct.isPresent()) {
            Product updatedProduct = buildProduct(quantity, optionalProduct.get());
            cart.getProducts().remove(optionalProduct.get());
            cart.getProducts().add(updatedProduct);
            updateCartTotalPrice(cart);
        } else {
            product.getQuantity().setQuantity(0.0);
            Product updatedProduct = buildProduct(quantity, product);
            cart.getProducts().add(updatedProduct);
            updateCartTotalPrice(cart);
        }

        return cart;
    }

    private Product buildProduct(Double quantity, Product product) {
        return Product.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .storeId(product.getStoreId())
                .quantity(new Quantity(quantity + product.getQuantity().getQuantity(), product.getQuantity().getUnit()))
                .build();
    }

    private void updateCartTotalPrice(Cart cart) {
        Double updatedPrice = cart.getProducts().stream().map(Product::getPrice).reduce(0.0, Double::sum);
        cart.setTotalPrice(updatedPrice);
    }

    private Mono<Cart> findOrCreateNewCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .switchIfEmpty(cartRepository.save(Cart.builder()
                        .userId(userId)
                        .products(new ArrayList<>())
                        .build()));
    }
}
