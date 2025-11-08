package com.blandev.lottery.backend.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandev.lottery.backend.infrastructure.persistence.entity.CustomerEntity;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

}
