package com.store.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String hno;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private Long zipCode;
}
