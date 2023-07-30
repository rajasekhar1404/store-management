package com.store.dto;

import com.store.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private String id;
    private String userId;
    private List<Product> products;
    private Double totalPrice;
}
