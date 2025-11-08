package com.blandev.lottery.backend.domain.repository;

import java.util.List;
import java.util.Optional;

import com.blandev.lottery.backend.domain.model.LotteryTicket;

public interface LotteryTicketRepository {
  LotteryTicket save(LotteryTicket lotteryTicket);

  List<LotteryTicket> saveAll(List<LotteryTicket> lotteryTickets);

  Optional<LotteryTicket> findById(Long id);

  Optional<LotteryTicket> findByDrawIdAndNumber(Long drawId, String number);

  List<LotteryTicket> findByDrawId(Long drawId);
}
