package com.store.modal;

import lombok.Data;

@Data
public class CreateStoreRequest {

    private String name;
    private String ownerId;
    private Address address;

}
