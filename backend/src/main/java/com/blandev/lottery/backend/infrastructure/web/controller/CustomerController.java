package com.blandev.lottery.backend.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.lottery.backend.application.dto.customer.CreateCustomerDTO;
import com.blandev.lottery.backend.application.dto.customer.CustomerResponseDTO;
import com.blandev.lottery.backend.application.usecase.customer.CreateCustomerUseCase;
import com.blandev.lottery.backend.application.usecase.customer.GetAllCustomersUseCase;
import com.blandev.lottery.backend.application.usecase.customer.GetCustomerByDocumentNumberUseCase;
import com.blandev.lottery.backend.application.usecase.customer.GetCustomerByIdUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetCustomerByDocumentNumberUseCase getCustomerByDocumentNumberUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(GetAllCustomersUseCase getAllCustomersUseCase,
            GetCustomerByIdUseCase getCustomerByIdUseCase,
            GetCustomerByDocumentNumberUseCase getCustomerByDocumentNumberUseCase,
            CreateCustomerUseCase createCustomerUseCase) {
        this.getAllCustomersUseCase = getAllCustomersUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
        this.getCustomerByDocumentNumberUseCase = getCustomerByDocumentNumberUseCase;
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        List<CustomerResponseDTO> responses = getAllCustomersUseCase.execute();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO response = getCustomerByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByDocumentNumber(@PathVariable String documentNumber) {
        CustomerResponseDTO response = getCustomerByDocumentNumberUseCase.execute(documentNumber);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CreateCustomerDTO request) {
        CustomerResponseDTO response = createCustomerUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
