package com.store.utils;

import com.store.modal.CreateProductRequest;

import java.util.Objects;

public class ProductValidator {
    public static CreateProductRequest isValidProduct(CreateProductRequest createProductRequest) {
        assert createProductRequest != null;
        assert !Objects.requireNonNull(createProductRequest).getName().isEmpty();
        assert !Objects.requireNonNull(createProductRequest).getStoreId().isEmpty();
        assert Objects.requireNonNull(createProductRequest).getQuantity() != null;
        return createProductRequest;
    }
}
