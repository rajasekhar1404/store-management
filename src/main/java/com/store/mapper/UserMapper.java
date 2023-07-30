package com.store.mapper;

import com.store.dto.UserDTO;
import com.store.entity.User;
import com.store.enums.ROLE;
import com.store.modal.CreateUserRequest;
import reactor.core.publisher.Mono;

import java.util.List;

public class UserMapper {

    public static Mono<User> mapCreateUserRequestToUser(Mono<CreateUserRequest> createUserRequestmono) {
        return createUserRequestmono.map(createUserRequest -> User.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .role(ROLE.USER)
                .addresses(List.of(createUserRequest.getAddress()))
                .build()
        );
    }

    public static Mono<UserDTO> mapUserToUserDTO(Mono<User> userMono) {
        return userMono.map(user -> UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .addresses(user.getAddresses())
                .build()
        );
    }

    public static UserDTO mapUserToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .addresses(user.getAddresses())
                .build();
    }

    public static User mapUserToUser(CreateUserRequest createUserRequest, User oldUser) {
        if (!createUserRequest.getFirstName().isEmpty()) oldUser.setFirstName(createUserRequest.getFirstName());
        if (!createUserRequest.getLastName().isEmpty()) oldUser.setLastName(createUserRequest.getLastName());
        if (!createUserRequest.getEmail().isEmpty()) oldUser.setEmail(createUserRequest.getEmail());
        if (createUserRequest.getAddress() != null) oldUser.setAddresses(List.of(createUserRequest.getAddress()));
        return oldUser;
    }

}
