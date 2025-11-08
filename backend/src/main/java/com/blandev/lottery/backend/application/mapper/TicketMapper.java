package com.blandev.lottery.backend.application.mapper;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.domain.model.LotteryTicket;

@Component
public class TicketMapper {

    public TicketResponseDTO toResponseDTO(LotteryTicket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getNumber(),
                ticket.getPrice(),
                ticket.getStatus().name(),
                ticket.getDrawId());
    }

}
