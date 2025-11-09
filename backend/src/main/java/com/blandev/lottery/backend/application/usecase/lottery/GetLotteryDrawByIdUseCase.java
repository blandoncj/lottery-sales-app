package com.blandev.lottery.backend.application.usecase.lottery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.application.mapper.LotteryDrawMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@Service
public class GetLotteryDrawByIdUseCase {

    private final LotteryDrawRepository lotteryDrawRepository;

    public GetLotteryDrawByIdUseCase(LotteryDrawRepository lotteryDrawRepository) {
        this.lotteryDrawRepository = lotteryDrawRepository;
    }

    @Transactional(readOnly = true)
    public LotteryDrawResponseDTO execute(Long id) {
        return lotteryDrawRepository.findById(id)
                .map(LotteryDrawMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Lottery draw", id));
    }

}
