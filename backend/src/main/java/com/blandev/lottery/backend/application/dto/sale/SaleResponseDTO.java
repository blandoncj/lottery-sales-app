package com.blandev.lottery.backend.application.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;

public record SaleResponseDTO(
    Long id,
    Long customerId,
    List<TicketResponseDTO> tickets,
    BigDecimal totalAmount,
    LocalDateTime saleDate) {
}
