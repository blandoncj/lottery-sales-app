package com.blandev.lottery.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.blandev.lottery.backend.domain.model.LotteryDraw;

public interface LotteryDrawRepository {
  LotteryDraw save(LotteryDraw lotteryDraw);

  Optional<LotteryDraw> findById(Long id);

  Optional<LotteryDraw> findByName(String name);

  List<LotteryDraw> findAll();
}
