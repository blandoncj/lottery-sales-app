package com.blandev.lottery.backend.application.usecase.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.application.mapper.CustomerMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;

@Service
public class GetCustomerByDocumentNumberUseCase {

    private final CustomerRepository customerRepository;

    public GetCustomerByDocumentNumberUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO execute(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber)
                .map(CustomerMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Customer with document number %s not found", documentNumber)));

    }

}
