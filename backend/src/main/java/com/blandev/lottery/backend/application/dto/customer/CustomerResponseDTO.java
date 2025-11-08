package com.blandev.lottery.backend.application.dto.customer;

public record CustomerResponseDTO(
    Long id,
    String documentNumber,
    String name,
    String email) {
}
