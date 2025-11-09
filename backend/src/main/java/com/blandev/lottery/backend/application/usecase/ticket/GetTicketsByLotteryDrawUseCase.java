package com.blandev.lottery.backend.application.usecase.ticket;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.application.mapper.TicketMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;

@Service
public class GetTicketsByLotteryDrawUseCase {

    private final LotteryTicketRepository lotteryTicketRepository;
    private final TicketMapper ticketMapper;

    public GetTicketsByLotteryDrawUseCase(LotteryTicketRepository lotteryTicketRepository,
            TicketMapper ticketMapper) {
        this.lotteryTicketRepository = lotteryTicketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Transactional(readOnly = true)
    public List<TicketResponseDTO> execute(Long drawId) {
        validateLotteryDrawExists(drawId);

        List<LotteryTicket> tickets = lotteryTicketRepository.findByDrawId(drawId);

        return tickets.stream()
                .map(ticketMapper::toResponseDTO)
                .toList();
    }

    private void validateLotteryDrawExists(Long drawId) {
        if (lotteryTicketRepository.findById(drawId).isEmpty()) {
            throw new ResourceNotFoundException("Lottery draw", drawId);
        }
    }

}
