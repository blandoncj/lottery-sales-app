package com.blandev.lottery.backend.application.mapper;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.domain.model.Customer;

@Component
public class CustomerMapper {

    public static CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getDocumentNumber(),
                customer.getName(),
                customer.getEmail());
    }

}
