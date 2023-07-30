package com.store.repo;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface ProductRepository extends ReactiveCouchbaseRepository<Product, String> {

    Flux<Product> findByStoreId(String storeId);

    Flux<Product> findByPriceBetween(Double min, Double max);

    Flux<Product> findByName(String name);
}
