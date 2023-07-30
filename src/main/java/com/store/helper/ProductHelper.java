package com.store.helper;

import com.store.entity.Product;
import com.store.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
abstract public class ProductHelper {

    @Autowired
    private ProductRepository productRepository;

    protected Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

}
