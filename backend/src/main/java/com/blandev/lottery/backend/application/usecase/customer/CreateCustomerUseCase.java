package com.blandev.lottery.backend.application.usecase.customer;

import org.springframework.stereotype.Service;

import com.blandev.lottery.backend.application.dto.customer.CreateCustomerDTO;
import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.application.mapper.CustomerMapper;
import com.blandev.lottery.backend.domain.exception.DuplicateResourceException;
import com.blandev.lottery.backend.domain.model.Customer;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {

  private final CustomerRepository customerRepository;

  public CreateCustomerUseCase(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Transactional
  public CustomerResponseDTO execute(CreateCustomerDTO dto) {
    validateUniqueConstraints(dto);

    Customer customer = new Customer(dto.documentNumber(), dto.name(), dto.email());
    Customer savedCustomer = customerRepository.save(customer);

    return CustomerMapper.toResponseDTO(savedCustomer);
  }

  private void validateUniqueConstraints(CreateCustomerDTO dto) {
    if (customerRepository.existsByDocumentNumber(dto.documentNumber())) {
      throw new DuplicateResourceException("Customer", "document number", dto.documentNumber());
    }

    if (customerRepository.existsByEmail(dto.email())) {
      throw new DuplicateResourceException("Customer", "email", dto.email());
    }
  }
}
