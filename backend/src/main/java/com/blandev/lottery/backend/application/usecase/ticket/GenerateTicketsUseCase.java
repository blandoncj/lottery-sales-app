package com.blandev.lottery.backend.application.usecase.ticket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.ticket.GenerateTicketsDTO;
import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.application.mapper.TicketMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;

@Service
public class GenerateTicketsUseCase {

    private final LotteryTicketRepository ticketRepository;
    private final LotteryDrawRepository drawRepository;
    private final TicketMapper ticketMapper;

    public GenerateTicketsUseCase(LotteryTicketRepository ticketRepository,
            LotteryDrawRepository drawRepository,
            TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.drawRepository = drawRepository;
        this.ticketMapper = ticketMapper;
    }

    @Transactional
    public List<TicketResponseDTO> execute(GenerateTicketsDTO dto) {
        validateDrawExists(dto.drawId());

        List<LotteryTicket> tickets = generateTickets(dto);

        List<LotteryTicket> savedTickets = ticketRepository.saveAll(tickets);

        return savedTickets.stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    private List<LotteryTicket> generateTickets(GenerateTicketsDTO dto) {
        List<LotteryTicket> tickets = new ArrayList<>();

        for (int i = 0; i < dto.quantity(); i++) {
            String ticketNumber = formatTicketNumber(1 + i);
            LotteryTicket ticket = new LotteryTicket(ticketNumber, dto.price(), dto.drawId());
            tickets.add(ticket);
        }

        return tickets;
    }

    private void validateDrawExists(Long drawId) {
        if (drawRepository.findById(drawId).isEmpty()) {
            throw new ResourceNotFoundException("Lottery draw", drawId);
        }
    }

    private String formatTicketNumber(int number) {
        return String.format("%06d", number);
    }

}
