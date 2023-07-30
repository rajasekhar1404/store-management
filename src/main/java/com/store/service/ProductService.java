package com.store.service;

import com.store.dto.ProductDTO;
import com.store.modal.CreateProductRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProductService {

    Mono<ProductDTO> create(Mono<CreateProductRequest> createProductRequestMono);

    Mono<ProductDTO> findByProductId(String id);

    Flux<ProductDTO> findByName(String name);

    Flux<ProductDTO> findByPriceRange(Double min, Double max);

    Flux<ProductDTO> findByStoreId(String storeId);

    Mono<ProductDTO> updateProduct(CreateProductRequest createProductRequest);

    Mono<Void> deleteProduct(String id);

}
