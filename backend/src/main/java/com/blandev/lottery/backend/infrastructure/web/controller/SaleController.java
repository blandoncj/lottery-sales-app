package com.blandev.lottery.backend.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.usecase.sale.GetAllSalesUseCase;
import com.blandev.lottery.backend.application.usecase.sale.GetSaleByIdUseCase;
import com.blandev.lottery.backend.application.usecase.sale.GetSalesByCustomerUseCase;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final GetAllSalesUseCase getAllSalesUseCase;
    private final GetSaleByIdUseCase getSaleByIdUseCase;
    private final GetSalesByCustomerUseCase getSalesByCustomerUseCase;

    public SaleController(GetAllSalesUseCase getAllSalesUseCase,
            GetSaleByIdUseCase getSaleByIdUseCase,
            GetSalesByCustomerUseCase getSalesByCustomerUseCase) {
        this.getAllSalesUseCase = getAllSalesUseCase;
        this.getSaleByIdUseCase = getSaleByIdUseCase;
        this.getSalesByCustomerUseCase = getSalesByCustomerUseCase;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = getAllSalesUseCase.execute();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        SaleResponseDTO sale = getSaleByIdUseCase.execute(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<SaleResponseDTO>> getSalesByCustomer(@PathVariable Long customerId) {
        List<SaleResponseDTO> sales = getSalesByCustomerUseCase.execute(customerId);
        return ResponseEntity.ok(sales);
    }

}
