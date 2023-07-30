package com.store.repo;

import com.store.dto.StoreDTO;
import com.store.entity.Store;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface StoreRepository extends ReactiveCouchbaseRepository<Store, String> {

    Mono<Store> findByName(String name);

    Flux<Store> findByOwnerId(String ownerId);
}
