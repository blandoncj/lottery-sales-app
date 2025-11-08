package com.blandev.lottery.backend.application.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDTO(
    @NotBlank(message = "Document number is required") String documentNumber,
    @NotBlank(message = "Name is required") String name,
    @NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email) {
}
