package com.blandev.lottery.backend.application.usecase.lottery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.lottery.CreateLotteryDrawDTO;
import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.application.mapper.LotteryDrawMapper;
import com.blandev.lottery.backend.domain.exception.DuplicateResourceException;
import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@Service
public class CreateLotteryDrawUseCase {

  private final LotteryDrawRepository lotteryDrawRepository;
  private final LotteryDrawMapper lotteryDrawMapper;

  public CreateLotteryDrawUseCase(LotteryDrawRepository lotteryDrawRepository, LotteryDrawMapper lotteryDrawMapper) {
    this.lotteryDrawRepository = lotteryDrawRepository;
    this.lotteryDrawMapper = lotteryDrawMapper;
  }

  @Transactional
  public LotteryDrawResponseDTO execute(CreateLotteryDrawDTO dto) {
    validateName(dto.name());

    LotteryDraw draw = new LotteryDraw(dto.name(), dto.drawDate());
    LotteryDraw savedDraw = lotteryDrawRepository.save(draw);

    return lotteryDrawMapper.toResponseDTO(savedDraw);
  }

  private void validateName(String name) {
    if (lotteryDrawRepository.findByName(name).isPresent()) {
      throw new DuplicateResourceException("Lottery draw", "name", name);
    }
  }

}
