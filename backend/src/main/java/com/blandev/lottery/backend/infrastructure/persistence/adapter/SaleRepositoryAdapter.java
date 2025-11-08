package com.blandev.lottery.backend.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.domain.repository.SaleRepository;
import com.blandev.lottery.backend.infrastructure.persistence.entity.CustomerEntity;
import com.blandev.lottery.backend.infrastructure.persistence.entity.LotteryTicketEntity;
import com.blandev.lottery.backend.infrastructure.persistence.entity.SaleEntity;
import com.blandev.lottery.backend.infrastructure.persistence.mapper.SaleEntityMapper;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaCustomerRepository;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaLotteryTicketRepository;
import com.blandev.lottery.backend.infrastructure.persistence.repository.JpaSaleRepository;

@Component
public class SaleRepositoryAdapter implements SaleRepository {

    private final JpaSaleRepository jpaRepository;
    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaLotteryTicketRepository jpaTicketRepository;

    public SaleRepositoryAdapter(JpaSaleRepository jpaRepository,
            JpaCustomerRepository jpaCustomerRepository,
            JpaLotteryTicketRepository jpaTicketRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Override
    public Sale save(Sale sale) {
        SaleEntity entity = SaleEntityMapper.toEntity(sale);

        CustomerEntity customerEntity = jpaCustomerRepository.findById(sale.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", sale.getCustomerId()));
        entity.setCustomer(customerEntity);

        if (sale.getTickets() != null && !sale.getTickets().isEmpty()) {
            List<LotteryTicketEntity> ticketEntities = sale.getTickets().stream()
                    .map(ticket -> jpaTicketRepository.findById(ticket.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Lottery ticket", ticket.getId())))
                    .toList();
            entity.setTickets(ticketEntities);
        }

        SaleEntity saved = jpaRepository.save(entity);
        return SaleEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Sale> findById(Long id) {
        return jpaRepository.findById(id)
                .map(SaleEntityMapper::toDomain);
    }

    @Override
    public List<Sale> findAll() {
        return jpaRepository.findAll().stream()
                .map(SaleEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Sale> findByCustomerId(Long customerId) {
        return jpaRepository.findByCustomerId(customerId).stream()
                .map(SaleEntityMapper::toDomain)
                .toList();
    }

}
