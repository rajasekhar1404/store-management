package com.store.mapper;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.modal.CreateProductRequest;
import reactor.core.publisher.Mono;

public class ProductMapper {

    public static Mono<Product> mapCreateProductRequestToProduct(Mono<CreateProductRequest> createProductRequestMono) {
        return createProductRequestMono.map(createProductRequest -> Product.builder()
                    .name(createProductRequest.getName())
                    .storeId(createProductRequest.getStoreId())
                    .quantity(createProductRequest.getQuantity())
                    .price(createProductRequest.getPrice())
                    .build()
        );
    }

    public static Mono<ProductDTO> mapProductToProductDTO(Mono<Product> productMono) {
        return productMono.map(product -> ProductDTO.builder()
                .name(product.getName())
                .storeId(product.getStoreId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build()
        );
    }

    public static ProductDTO mapProductToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .storeId(product.getStoreId())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

}
