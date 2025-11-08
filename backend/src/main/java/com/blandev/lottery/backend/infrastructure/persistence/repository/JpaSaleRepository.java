package com.blandev.lottery.backend.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandev.lottery.backend.infrastructure.persistence.entity.SaleEntity;

public interface JpaSaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByCustomerId(Long customerId);

}
