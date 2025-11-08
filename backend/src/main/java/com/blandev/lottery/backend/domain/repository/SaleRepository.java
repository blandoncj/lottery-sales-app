package com.blandev.lottery.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.blandev.lottery.backend.domain.model.Sale;

public interface SaleRepository {
  Sale save(Sale sale);

  Optional<Sale> findById(Long id);

  List<Sale> findAll();

  List<Sale> findByCustomerId(Long customerId);
}
