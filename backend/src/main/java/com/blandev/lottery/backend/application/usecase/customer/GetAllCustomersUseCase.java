package com.blandev.lottery.backend.application.usecase.customer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.application.mapper.CustomerMapper;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;

@Service
public class GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    public GetAllCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> execute() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponseDTO)
                .toList();
    }
}
