package com.blandev.lottery.backend.application.usecase.sale;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.mapper.SaleMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@Service
public class GetSalesByCustomerUseCase {

  private final SaleRepository saleRepository;
  private final CustomerRepository customerRepository;
  private final SaleMapper saleMapper;

  public GetSalesByCustomerUseCase(SaleRepository saleRepository,
      CustomerRepository customerRepository,
      SaleMapper saleMapper) {
    this.saleRepository = saleRepository;
    this.customerRepository = customerRepository;
    this.saleMapper = saleMapper;
  }

  @Transactional(readOnly = true)
  public List<SaleResponseDTO> execute(Long customerId) {
    validateCustomerExists(customerId);

    List<Sale> sales = saleRepository.findByCustomerId(customerId);

    return sales.stream()
        .map(saleMapper::toResponseDTO)
        .toList();
  }

  private void validateCustomerExists(Long customerId) {
    if (!customerRepository.findById(customerId).isPresent()) {
      throw new ResourceNotFoundException("Customer", customerId);
    }
  }
}
