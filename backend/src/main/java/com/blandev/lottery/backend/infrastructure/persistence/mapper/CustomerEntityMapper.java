package com.blandev.lottery.backend.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.model.Customer;
import com.blandev.lottery.backend.infrastructure.persistence.entity.CustomerEntity;

@Component
public class CustomerEntityMapper {

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getDocumentNumber(),
                entity.getName(),
                entity.getEmail());
    }

    public static CustomerEntity toEntity(Customer domain) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(domain.getId());
        entity.setDocumentNumber(domain.getDocumentNumber());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        return entity;
    }

}
