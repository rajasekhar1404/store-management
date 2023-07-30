package com.store.repo;

import com.store.entity.User;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCouchbaseRepository<User, String> {

    Mono<User> findByEmail(String email);

    Flux<User> findByAddressesContaining(Long zip);
}
