package com.blandev.lottery.backend.infrastructure.persistence.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryDrawEntity;

@Component
public class LotteryDrawEntityMapper {

    public static LotteryDraw toDomain(LotteryDrawEntity entity) {
        List<LotteryTicket> tickets = entity.getTickets() != null
                ? entity.getTickets().stream()
                        .map(LotteryTicketEntityMapper::toDomain)
                        .collect(Collectors.toList())
                : Collections.emptyList();

        return new LotteryDraw(
                entity.getId(),
                entity.getName(),
                entity.getDrawDate(),
                tickets);
    }

    public static LotteryDrawEntity toEntity(LotteryDraw domain) {
        LotteryDrawEntity entity = new LotteryDrawEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDrawDate(domain.getDrawDate());

        return entity;
    }

}
