package com.blandev.lottery.backend.application.dto.sale;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SellTicketDTO(
    @NotNull(message = "Customer ID is required") Long customerId,
    @NotEmpty(message = "At least one ticket ID is required") List<Long> ticketIds) {
}
