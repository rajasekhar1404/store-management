package com.store.service;

import com.store.dto.StoreDTO;
import com.store.modal.CreateStoreRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface StoreService {

    Mono<StoreDTO> register(Mono<CreateStoreRequest> createStoreRequestMono);

    Mono<StoreDTO> findById(String id);

    Mono<StoreDTO> findByName(String name);

    Flux<StoreDTO> findByOwnerId(String ownerId);

    Flux<StoreDTO> findByCountry(String country);

    Flux<StoreDTO> findByState(String state);

    Flux<StoreDTO> findByCity(String city);

    Flux<StoreDTO> findByZipCode(Long zipCode);

    Flux<StoreDTO> findAll();
}
