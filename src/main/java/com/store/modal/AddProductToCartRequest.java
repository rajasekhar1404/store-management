package com.store.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToCartRequest {

    private String userId;
    private String productId;
    private Double quantity;

}
