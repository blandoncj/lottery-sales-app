package com.blandev.lottery.backend.application.usecase.ticket;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.application.mapper.TicketMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;

@Service
public class GetTicketsByLotteryDrawUseCase {

    private final LotteryTicketRepository lotteryTicketRepository;
    private final LotteryDrawRepository lotteryDrawRepository;

    public GetTicketsByLotteryDrawUseCase(LotteryTicketRepository lotteryTicketRepository,
            LotteryDrawRepository lotteryDrawRepository) {
        this.lotteryTicketRepository = lotteryTicketRepository;
        this.lotteryDrawRepository = lotteryDrawRepository;
    }

    @Transactional(readOnly = true)
    public List<TicketResponseDTO> execute(Long drawId) {
        validateLotteryDrawExists(drawId);

        List<LotteryTicket> tickets = lotteryTicketRepository.findByDrawId(drawId);

        return tickets.stream()
                .map(TicketMapper::toResponseDTO)
                .toList();
    }

    private void validateLotteryDrawExists(Long drawId) {
        if (lotteryDrawRepository.findById(drawId).isEmpty()) {
            throw new ResourceNotFoundException("Lottery draw", drawId);
        }
    }

}
