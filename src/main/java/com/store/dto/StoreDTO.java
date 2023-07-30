package com.store.dto;

import com.store.modal.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreDTO {
    private String id;
    private String name;
    private String ownerId;
    private Address address;
}
