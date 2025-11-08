package com.blandev.lottery.backend.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryTicketEntity;

@Component
public class LotteryTicketEntityMapper {

    public static LotteryTicket toDomain(LotteryTicketEntity entity) {
        return new LotteryTicket(
                entity.getId(),
                entity.getNumber(),
                entity.getPrice(),
                entity.getStatus(),
                entity.getLotteryDraw().getId());
    }

    public static LotteryTicketEntity toEntity(LotteryTicket domain) {
        LotteryTicketEntity entity = new LotteryTicketEntity();
        entity.setId(domain.getId());
        entity.setNumber(domain.getNumber());
        entity.setPrice(domain.getPrice());
        entity.setStatus(domain.getStatus());

        return entity;
    }

}
