package com.blandev.lottery.backend.infrastructure.persistence.mapper;

import java.util.List;

import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.infrastructure.persistence.entity.SaleEntity;

public class SaleEntityMapper {

    public static Sale toDomain(SaleEntity entity) {
        List<LotteryTicket> tickets = entity.getTickets().stream()
                .map(LotteryTicketEntityMapper::toDomain)
                .toList();

        return new Sale(
                entity.getId(),
                entity.getCustomer().getId(),
                tickets,
                entity.getTotalAmount(),
                entity.getSaleDate());
    }

    public static SaleEntity toEntity(Sale sale) {
        SaleEntity entity = new SaleEntity();
        entity.setId(sale.getId());
        entity.setTotalAmount(sale.getTotalAmount());
        entity.setSaleDate(sale.getSaleDate());

        return entity;
    }

}
