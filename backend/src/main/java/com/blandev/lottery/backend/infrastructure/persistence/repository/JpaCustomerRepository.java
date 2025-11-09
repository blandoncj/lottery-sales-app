package com.blandev.lottery.backend.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandev.lottery.backend.infrastructure.persistence.entity.CustomerEntity;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByDocumentNumber(String documentNumber);

    boolean existsByDocumentNumber(String documentNumber);

    boolean existsByEmail(String email);

}
