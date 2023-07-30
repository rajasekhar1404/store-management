package com.store.repo;

import com.store.dto.CartDTO;
import com.store.entity.Cart;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CartRepository extends ReactiveCouchbaseRepository<Cart, String> {

    Mono<Cart> findByUserId(String userId);
}
