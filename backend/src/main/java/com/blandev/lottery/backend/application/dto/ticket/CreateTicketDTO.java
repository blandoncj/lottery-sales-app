package com.blandev.lottery.backend.application.dto.ticket;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTicketDTO(
    @NotNull(message = "Draw ID is required") Long drawId,
    @NotBlank(message = "Number is required") String number,
    @NotNull(message = "Price is required") @Positive(message = "Price must be positive") BigDecimal price) {
}
