package com.blandev.lottery.backend.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryDrawEntity;

public interface JpaLotteryDrawRepository extends JpaRepository<LotteryDrawEntity, Long> {

    Optional<LotteryDrawEntity> findByName(String name);

}
