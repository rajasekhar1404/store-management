package com.store.router;

import com.store.dto.UserDTO;
import com.store.enums.ROLE;
import com.store.handler.UserHandler;
import com.store.modal.Address;
import com.store.modal.CreateUserRequest;
import com.store.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebFluxTest
class UserRouterTest {

    private WebTestClient webTestClient;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        UserHandler userHandler = new UserHandler(userService);
        RouterFunction<ServerResponse> routes = new UserRouter().userRoutes(userHandler);
        webTestClient = WebTestClient.bindToRouterFunction(routes).build();
    }

    @Test
    void getUserById() {

        Flux<String> responseBody = webTestClient
                .get()
                .uri("/")
                .exchange()
                .returnResult(String.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext("Message")
                .verifyComplete();
    }

    @Test
    void findById() {
        String id = "2255b217-9c56-4cd5-a7fc-4522659902a2";

        UserDTO userDTO = UserDTO.builder()
                .id("2255b217-9c56-4cd5-a7fc-4522659902a2")
                .email("user@gmail.com")
                .firstName("firstName")
                .lastName("lastName")
                .role(ROLE.USER)
                .build();

        Mono<UserDTO> userDTOMono = Mono.just(userDTO);
        when(userService.findById(id)).thenReturn(userDTOMono);

        Flux<UserDTO> responseBody = webTestClient.get().uri("/user/id/" + id)
                .exchange()
                .returnResult(UserDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(userDTO)
                .verifyComplete();

    }

    @Test
    void registerUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest("firstName", "lastName", "email@gmail.com", "password", new Address());
        UserDTO userDTO = UserDTO.builder().id("2255b217-9c56-4cd5-a7fc-4522659902a2").email("user@gmail.com").firstName("firsName").lastName("lastName").role(ROLE.USER).addresses(new ArrayList<>()).build();
        Mono<CreateUserRequest> just = Mono.just(createUserRequest);

        when(userService.register(any())).thenReturn(Mono.just(userDTO));

        Flux<UserDTO> responseBody = webTestClient.post().uri("/user/register")
                .body(just, CreateUserRequest.class)
                .exchange()
                .returnResult(UserDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(userDTO)
                .verifyComplete();
    }

    @Test
    void findByZip() {

        Long zip = 508221L;

        UserDTO userDTO = UserDTO.builder()
                .id("2255b217-9c56-4cd5-a7fc-4522659902a2")
                .email("user@gmail.com")
                .firstName("firstName")
                .lastName("lastName")
                .role(ROLE.USER)
                .build();

        when(userService.findUsersByZip(zip)).thenReturn(Flux.just(userDTO));

        Flux<UserDTO> responseBody = webTestClient.get().uri("/user/zip/" + zip)
                .exchange()
                .returnResult(UserDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(user -> user.getEmail().equals("user@gmail.com"))
                .verifyComplete();
    }

    @Test
    void findByEmail() {

        String email = "user@gmail.com";

        UserDTO userDTO = UserDTO.builder()
                .id("2255b217-9c56-4cd5-a7fc-4522659902a2")
                .email("user@gmail.com")
                .firstName("firstName")
                .lastName("lastName")
                .role(ROLE.USER)
                .build();

        when(userService.findByEmail(email)).thenReturn(Mono.just(userDTO));

        Flux<UserDTO> responseBody = webTestClient.get().uri("/user/email/" + email)
                .exchange()
                .returnResult(UserDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(user -> user.getEmail().equals(email))
                .verifyComplete();
    }

    @Test
    void findByCountry() {
        String country = "India";

        UserDTO userDTO = UserDTO.builder().id("2255b217-9c56-4cd5-a7fc-4522659902a2").email("user@gmail.com").firstName("firsName").lastName("lastName").role(ROLE.USER).addresses(List.of(Address.builder().country("India").build())).build();

        when(userService.findByCountry(country)).thenReturn(Flux.just(userDTO));

        Flux<UserDTO> responseBody = webTestClient.get().uri("/user/country/" + country)
                .exchange()
                .returnResult(UserDTO.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(user -> user.getAddresses().stream().anyMatch(address -> address.getCountry().equals("India")))
                .verifyComplete();
    }
}