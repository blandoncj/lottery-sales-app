package com.blandev.lottery.backend.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryTicketEntity;

public interface JpaLotteryTicketRepository extends JpaRepository<LotteryTicketEntity, Long> {

    List<LotteryTicketEntity> findByLotteryDrawId(Long lotteryDrawId);

    Optional<LotteryTicketEntity> findByLotteryDraw_IdAndNumber(Long drawId, String number);

}
