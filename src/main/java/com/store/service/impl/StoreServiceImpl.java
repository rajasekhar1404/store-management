package com.store.service.impl;

import com.store.dto.StoreDTO;
import com.store.mapper.StoreMapper;
import com.store.modal.CreateStoreRequest;
import com.store.repo.StoreRepository;
import com.store.service.StoreService;
import com.store.utils.StoreValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Mono<StoreDTO> register(Mono<CreateStoreRequest> createStoreRequestMono) {
        Mono<CreateStoreRequest> validCreateStore = StoreValidator.isValidCreateStore(createStoreRequestMono);
        return StoreMapper.mapStoreToStoreDTO(
                    StoreMapper.mapCreateStoreRequestToStore(validCreateStore)
                .flatMap(storeRepository::save));
    }

    @Override
    public Flux<StoreDTO> findAll() {
        return storeRepository.findAll().map(StoreMapper::mapStoreToStoreDTO);
    }

    @Override
    public Mono<StoreDTO> findById(String id) {
        return storeRepository.findById(id)
                .map(StoreMapper::mapStoreToStoreDTO);
    }

    @Override
    public Mono<StoreDTO> findByName(String name) {
        return storeRepository.findByName(name)
                .map(StoreMapper::mapStoreToStoreDTO);
    }

    @Override
    public Flux<StoreDTO> findByOwnerId(String ownerId) {
        return storeRepository.findByOwnerId(ownerId)
                .map(StoreMapper::mapStoreToStoreDTO);
    }


    @Override
    public Flux<StoreDTO> findByCountry(String country) {
        return storeRepository.findAll().map(StoreMapper::mapStoreToStoreDTO);
    }

    @Override
    public Flux<StoreDTO> findByState(String state) {
        return null;
    }

    @Override
    public Flux<StoreDTO> findByCity(String city) {
        return null;
    }

    @Override
    public Flux<StoreDTO> findByZipCode(Long zipCode) {
        return null;
    }
}
