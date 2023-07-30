package com.store.handler;

import com.store.dto.ProductDTO;
import com.store.modal.CreateProductRequest;
import com.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        Mono<CreateProductRequest> createProductRequestMono = request.bodyToMono(CreateProductRequest.class);
        return ServerResponse.ok().body(productService.create(createProductRequestMono), ProductDTO.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ServerResponse.ok().body(
                productService.findByProductId(request.pathVariable("id")), ProductDTO.class
        );
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        return ServerResponse.ok()
                .body(productService.findByName(request.pathVariable("name")), ProductDTO.class);
    }

    public Mono<ServerResponse> findByPriceRange(ServerRequest request) {

        Double min = Double.valueOf(request.queryParam("min").orElse("0"));
        Double max = Double.valueOf(request.queryParam("max").orElse("1000"));

        return ServerResponse.ok().body(
                productService.findByPriceRange(min, max), ProductDTO.class
        );
    }

    public Mono<ServerResponse> findByStoreId(ServerRequest request) {
        return ServerResponse.ok()
                .body(productService.findByStoreId(request.pathVariable("id")), ProductDTO.class);
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        return ServerResponse.ok().body(productService.deleteProduct(request.pathVariable("id")), Void.class);
    }

}
