package com.store.utils;

import com.store.modal.CreateUserRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class UserValidator {

    public static CreateUserRequest isValidUserRequest(CreateUserRequest createUserRequest) {
            assert createUserRequest != null;
            assert Objects.requireNonNull(createUserRequest).getFirstName() != null;
            assert Objects.requireNonNull(createUserRequest).getLastName() != null;
            assert Objects.requireNonNull(createUserRequest).getEmail() != null;
            assert Objects.requireNonNull(createUserRequest).getPassword() != null;
            return createUserRequest;
    }

}