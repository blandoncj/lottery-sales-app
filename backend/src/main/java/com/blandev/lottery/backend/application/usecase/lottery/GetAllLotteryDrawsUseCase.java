package com.blandev.lottery.backend.application.usecase.lottery;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.application.mapper.LotteryDrawMapper;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@Service
public class GetAllLotteryDrawsUseCase {

  private final LotteryDrawRepository lotteryDrawRepository;
  private final LotteryDrawMapper lotteryDrawMapper;

  public GetAllLotteryDrawsUseCase(LotteryDrawRepository lotteryDrawRepository, LotteryDrawMapper lotteryDrawMapper) {
    this.lotteryDrawRepository = lotteryDrawRepository;
    this.lotteryDrawMapper = lotteryDrawMapper;
  }

  @Transactional(readOnly = true)
  public List<LotteryDrawResponseDTO> execute() {
    return lotteryDrawRepository.findAll().stream()
        .map(lotteryDrawMapper::toResponseDTO)
        .toList();
  }
}
