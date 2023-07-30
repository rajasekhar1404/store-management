package com.store.service.impl;

import com.store.dto.ProductDTO;
import com.store.entity.Product;
import com.store.helper.ProductHelper;
import com.store.mapper.ProductMapper;
import com.store.modal.CreateProductRequest;
import com.store.repo.ProductRepository;
import com.store.service.ProductService;
import com.store.utils.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ProductHelper implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Mono<ProductDTO> create(Mono<CreateProductRequest> createProductRequestMono) {
        Mono<CreateProductRequest> validCreateProduct = createProductRequestMono.map(ProductValidator::isValidProduct);
        return ProductMapper.mapCreateProductRequestToProduct(validCreateProduct)
                .flatMap(productRepository::save).map(ProductMapper::mapProductToProductDTO);
    }

    @Override
    public Mono<ProductDTO> findByProductId(String id) {
        return super.findById(id)
                .map(ProductMapper::mapProductToProductDTO);
    }

    @Override
    public Flux<ProductDTO> findByName(String name) {
        return productRepository.findByName(name)
                .map(ProductMapper::mapProductToProductDTO);
    }

    @Override
    public Flux<ProductDTO> findByPriceRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max)
                .map(ProductMapper::mapProductToProductDTO);
    }

    @Override
    public Flux<ProductDTO> findByStoreId(String storeId) {
        return productRepository.findByStoreId(storeId)
                .map(ProductMapper::mapProductToProductDTO);
    }

    @Override
    public Mono<ProductDTO> updateProduct(CreateProductRequest createProductRequest) {
        return null;
    }

    @Override
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
