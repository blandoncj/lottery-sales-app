package com.blandev.lottery.backend.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.model.Sale;

@Component
public class SaleMapper {

  public SaleResponseDTO toResponseDTO(Sale sale) {
    List<TicketResponseDTO> ticketDTOs = sale.getTickets().stream()
        .map(this::toTicketResponseDTO)
        .toList();

    return new SaleResponseDTO(
        sale.getId(),
        sale.getCustomerId(),
        ticketDTOs,
        sale.getTotalAmount(),
        sale.getSaleDate());
  }

  private TicketResponseDTO toTicketResponseDTO(LotteryTicket ticket) {
    return new TicketResponseDTO(
        ticket.getId(),
        ticket.getNumber(),
        ticket.getPrice(),
        ticket.getStatus().name(),
        ticket.getDrawId());
  }
}
