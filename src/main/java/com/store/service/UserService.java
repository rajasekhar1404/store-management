package com.store.service;

import com.store.dto.UserDTO;
import com.store.modal.CreateUserRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserService {

    Mono<UserDTO> register(Mono<CreateUserRequest> createUserRequest);

    Mono<UserDTO> findById(String id);

    Mono<UserDTO> findByEmail(String email);

    Flux<UserDTO> findUsersByZip(Long zip);

    Flux<UserDTO> findByCountry(String country);

    Mono<UserDTO> updateUser(Mono<CreateUserRequest> createUserRequestMono, String id);

    Mono<Void> deleteById(String id);
}
