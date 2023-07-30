package com.store.entity;

import com.store.modal.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
@Collection(value = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    private String name;
    private String ownerId;
    private Address address;

}
