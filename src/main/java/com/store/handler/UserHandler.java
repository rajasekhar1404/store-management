package com.store.handler;

import com.store.dto.UserDTO;
import com.store.modal.CreateUserRequest;
import com.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserHandler {

    private final UserService userService;

    public Mono<ServerResponse> registerUser(ServerRequest request) {
        Mono<CreateUserRequest> createUserRequestMono = request.bodyToMono(CreateUserRequest.class);
        return ServerResponse.ok().body(userService.register(createUserRequestMono), UserDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ServerResponse.ok().body(
                userService.findById(request.pathVariable("id")), UserDTO.class);
    }

    public Mono<ServerResponse> findByZip(ServerRequest request) {
        return ServerResponse.ok().body(
          userService.findUsersByZip(Long.parseLong(request.pathVariable("zip"))), UserDTO.class
        );
    }

    public Mono<ServerResponse> findByEmail(ServerRequest request) {
        return ServerResponse.ok().body(
          userService.findByEmail(request.pathVariable("email")), UserDTO.class
        );
    }

    public Mono<ServerResponse> findByCountry(ServerRequest request) {
        return ServerResponse.ok().body(
                userService.findByCountry(request.pathVariable("country")), UserDTO.class
        );
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        return ServerResponse.ok()
                .body(
                        userService.updateUser(request.bodyToMono(CreateUserRequest.class), request.pathVariable("id"))
                                .doOnError(t -> System.out.println(t.getMessage())), UserDTO.class
                );
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ServerResponse.ok().body(
                userService.deleteById(request.pathVariable("id")), Void.class
        );
    }

    public Mono<ServerResponse> message(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("Message");
    }

}
