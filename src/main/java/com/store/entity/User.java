package com.store.entity;

import com.store.enums.ROLE;
import com.store.modal.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.repository.Collection;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
@Collection(value = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ROLE role;
    private List<Address> addresses;

}
