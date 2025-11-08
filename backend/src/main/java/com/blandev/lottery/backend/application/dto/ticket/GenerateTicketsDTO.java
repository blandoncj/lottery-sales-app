package com.blandev.lottery.backend.application.dto.ticket;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GenerateTicketsDTO(
        @NotNull(message = "Draw ID is required") Long drawId,
        @NotNull(message = "Quantity is required") @Min(value = 1, message = "Quantity must be at least 1") @Max(value = 10000, message = "Quantity cannot exceed 10000") Integer quantity,
        @NotNull(message = "Price is required") @Positive(message = "Price must be positive") BigDecimal price) {
}
