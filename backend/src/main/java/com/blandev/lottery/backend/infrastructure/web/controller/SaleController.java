package com.blandev.lottery.backend.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.usecase.sale.GetSalesByCustomerUseCase;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final GetSalesByCustomerUseCase getSalesByCustomerUseCase;

    public SaleController(GetSalesByCustomerUseCase getSalesByCustomerUseCase) {
        this.getSalesByCustomerUseCase = getSalesByCustomerUseCase;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleResponseDTO>> getSalesByCustomer(@PathVariable Long customerId) {
        List<SaleResponseDTO> sales = getSalesByCustomerUseCase.execute(customerId);
        return ResponseEntity.ok(sales);
    }

}
