package com.blandev.lottery.backend.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.model.Customer;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;
import com.blandev.lottery.backend.infrastructure.persistence.entity.CustomerEntity;
import com.blandev.lottery.backend.infrastructure.persistence.mapper.CustomerEntityMapper;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaCustomerRepository;

@Component
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final JpaCustomerRepository jpaRepository;

    public CustomerRepositoryAdapter(JpaCustomerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = CustomerEntityMapper.toEntity(customer);
        CustomerEntity saved = jpaRepository.save(entity);
        return CustomerEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id)
                .map(CustomerEntityMapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll().stream()
                .map(CustomerEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return jpaRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

}
