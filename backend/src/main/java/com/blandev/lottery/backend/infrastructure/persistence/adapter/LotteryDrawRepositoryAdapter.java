package com.blandev.lottery.backend.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryDrawEntity;
import com.blandev.lottery.backend.infrastructure.persistence.mapper.LotteryDrawEntityMapper;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaLotteryDrawRepository;

@Component
public class LotteryDrawRepositoryAdapter implements LotteryDrawRepository {

    private final JpaLotteryDrawRepository jpaRepository;

    public LotteryDrawRepositoryAdapter(JpaLotteryDrawRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public LotteryDraw save(LotteryDraw lotteryDraw) {
        LotteryDrawEntity entity = LotteryDrawEntityMapper.toEntity(lotteryDraw);
        LotteryDrawEntity saved = jpaRepository.save(entity);
        return LotteryDrawEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<LotteryDraw> findById(Long id) {
        return jpaRepository.findById(id)
                .map(LotteryDrawEntityMapper::toDomain);
    }

    @Override
    public Optional<LotteryDraw> findByName(String name) {
        return jpaRepository.findByName(name)
                .map(LotteryDrawEntityMapper::toDomain);
    }

    @Override
    public List<LotteryDraw> findAll() {
        return jpaRepository.findAll().stream()
                .map(LotteryDrawEntityMapper::toDomain)
                .toList();
    }

}
