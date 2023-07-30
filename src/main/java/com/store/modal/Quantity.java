package com.store.modal;

import com.store.enums.UNIT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quantity {

    private Double quantity;
    private UNIT unit;

}
