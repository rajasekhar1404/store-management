package com.store.mapper;

import com.store.dto.StoreDTO;
import com.store.entity.Store;
import com.store.modal.CreateStoreRequest;
import reactor.core.publisher.Mono;

public class StoreMapper {

    public static Mono<Store> mapCreateStoreRequestToStore(Mono<CreateStoreRequest> createStoreRequestMono) {
        return createStoreRequestMono.map(createStoreRequest -> Store.builder()
                .name(createStoreRequest.getName())
                .ownerId(createStoreRequest.getOwnerId())
                .address(createStoreRequest.getAddress())
                .build()
        );
    }

    public static Mono<StoreDTO> mapStoreToStoreDTO(Mono<Store> storeMono) {
        return storeMono.map(store -> StoreDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .ownerId(store.getOwnerId())
                .address(store.getAddress())
                .build()
        );
    }

    public static StoreDTO mapStoreToStoreDTO(Store store) {
        return StoreDTO.builder()
                .id(store.getId())
                .name(store.getName())
                .ownerId(store.getOwnerId())
                .address(store.getAddress())
                .build();
    }

}
