package com.blandev.lottery.backend.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryDrawEntity;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryTicketEntity;
import com.blandev.lottery.backend.infrastructure.persistence.mapper.LotteryTicketEntityMapper;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaLotteryDrawRepository;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaLotteryTicketRepository;

@Component
public class LotteryTicketRepositoryAdapter implements LotteryTicketRepository {

    private final JpaLotteryTicketRepository jpaRepository;
    private final JpaLotteryDrawRepository jpaDrawRepository;

    public LotteryTicketRepositoryAdapter(JpaLotteryTicketRepository jpaRepository,
            JpaLotteryDrawRepository jpaDrawRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaDrawRepository = jpaDrawRepository;
    }

    @Override
    public LotteryTicket save(LotteryTicket lotteryTicket) {
        LotteryTicketEntity entity = LotteryTicketEntityMapper.toEntity(lotteryTicket);

        LotteryDrawEntity drawEntity = jpaDrawRepository.findById(lotteryTicket.getDrawId())
                .orElseThrow(() -> new ResourceNotFoundException("Lottery draw", lotteryTicket.getDrawId()));
        entity.setLotteryDraw(drawEntity);

        LotteryTicketEntity saved = jpaRepository.save(entity);
        return LotteryTicketEntityMapper.toDomain(saved);
    }

    @Override
    public List<LotteryTicket> saveAll(List<LotteryTicket> lotteryTickets) {
        Long drawId = lotteryTickets.get(0).getDrawId(); // assuming all tickets belong to the same draw

        LotteryDrawEntity drawEntity = jpaDrawRepository.findById(drawId)
                .orElseThrow(() -> new ResourceNotFoundException("Lottery draw", drawId));

        List<LotteryTicketEntity> entities = lotteryTickets.stream()
                .map(ticket -> {
                    LotteryTicketEntity entity = LotteryTicketEntityMapper.toEntity(ticket);
                    entity.setLotteryDraw(drawEntity);
                    return entity;
                })
                .toList();

        List<LotteryTicketEntity> savedEntities = jpaRepository.saveAll(entities);

        return savedEntities.stream()
                .map(LotteryTicketEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<LotteryTicket> findById(Long id) {
        return jpaRepository.findById(id)
                .map(LotteryTicketEntityMapper::toDomain);
    }

    @Override
    public Optional<LotteryTicket> findByDrawIdAndNumber(Long drawId, String number) {
        return jpaRepository.findByLotteryDraw_IdAndNumber(drawId, number)
                .map(LotteryTicketEntityMapper::toDomain);
    }

    @Override
    public List<LotteryTicket> findByDrawId(Long drawId) {
        return jpaRepository.findByLotteryDrawId(drawId).stream()
                .map(LotteryTicketEntityMapper::toDomain)
                .toList();
    }

}
