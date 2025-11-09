package com.blandev.lottery.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.blandev.lottery.backend.domain.model.Customer;

public interface CustomerRepository {
  Customer save(Customer customer);

  Optional<Customer> findById(Long id);

  Optional<Customer> findByDocumentNumber(String documentNumber);

  List<Customer> findAll();

  boolean existsByDocumentNumber(String documentNumber);

  boolean existsByEmail(String email);
}
