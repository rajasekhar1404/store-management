package com.store.utils;

import com.store.modal.CreateStoreRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class StoreValidator {

    public static Mono<CreateStoreRequest> isValidCreateStore(Mono<CreateStoreRequest> createStoreRequestMono) {
        return createStoreRequestMono.map(createStoreRequest -> {
                    assert createStoreRequest != null;
                    assert Objects.requireNonNull(createStoreRequest).getName() != null;
                    assert Objects.requireNonNull(createStoreRequest).getOwnerId() != null;
                    assert Objects.requireNonNull(createStoreRequest).getAddress() != null;
                    return createStoreRequest;
                }
        );

    }

}
