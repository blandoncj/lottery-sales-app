package com.blandev.lottery.backend.application.usecase.lottery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.application.mapper.LotteryDrawMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@Service
public class GetLotteryDrawByNameUseCase {

    private final LotteryDrawRepository lotteryDrawRepository;

    public GetLotteryDrawByNameUseCase(LotteryDrawRepository lotteryDrawRepository) {
        this.lotteryDrawRepository = lotteryDrawRepository;
    }

    @Transactional(readOnly = true)
    public LotteryDrawResponseDTO execute(String name) {
        return lotteryDrawRepository.findByName(name)
                .map(LotteryDrawMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Lottery draw with name '%s' not found", name)));
    }

}
