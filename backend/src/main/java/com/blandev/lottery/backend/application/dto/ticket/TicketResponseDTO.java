package com.blandev.lottery.backend.application.dto.ticket;

import java.math.BigDecimal;

public record TicketResponseDTO(
    Long id,
    String number,
    BigDecimal price,
    String status,
    Long drawId) {
}
