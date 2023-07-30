package com.store.service.impl;

import com.store.dto.UserDTO;
import com.store.entity.User;
import com.store.mapper.UserMapper;
import com.store.modal.CreateUserRequest;
import com.store.repo.UserRepository;
import com.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.couchbase.core.ReactiveCouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.QueryCriteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReactiveCouchbaseTemplate couchbaseTemplate;

    @Override
    public Mono<UserDTO> register(Mono<CreateUserRequest> createUserRequest) {
        return UserMapper.mapCreateUserRequestToUser(createUserRequest)
                .map(userRepository::save)
                .flatMap(UserMapper::mapUserToUserDTO);
    }

    @Override
    public Mono<UserDTO> findById(String id) {
        return UserMapper.mapUserToUserDTO(userRepository.findById(id));
    }

    @Override
    public Mono<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::mapUserToUserDTO);
    }

    @Override
    public Flux<UserDTO> findUsersByZip(Long zip) {
        return couchbaseTemplate.findByQuery(User.class)
                .matching(Query.query(QueryCriteria.where("addresses.zipCode").is(zip)))
                .all().map(UserMapper::mapUserToUserDTO);
    }

    public Flux<UserDTO> findByCountry(String country) {
        return couchbaseTemplate.findByQuery(User.class)
//                .matching(Query.query(QueryCriteria.where("addresses.country").is(country)))
                .all()
                .map(UserMapper::mapUserToUserDTO);
    }

    @Override
    public Mono<UserDTO> updateUser(Mono<CreateUserRequest> createUserRequestMono, String id) {
        Mono<User> one = couchbaseTemplate.findById(User.class).one(id).switchIfEmpty(Mono.error(new RuntimeException("User not found")));
        Mono<User> userMono = one.flatMap(user -> createUserRequestMono.map(createUserRequest -> UserMapper.mapUserToUser(createUserRequest, user)));
        return userMono.map(user -> couchbaseTemplate.save(user)).flatMap(UserMapper::mapUserToUserDTO);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return couchbaseTemplate.findById(User.class)
                .one(id)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .then(userRepository.deleteById(id));
    }
}