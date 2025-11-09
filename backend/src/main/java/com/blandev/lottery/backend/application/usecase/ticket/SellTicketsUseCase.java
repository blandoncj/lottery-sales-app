package com.blandev.lottery.backend.application.usecase.ticket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.dto.sale.SellTicketDTO;
import com.blandev.lottery.backend.application.mapper.SaleMapper;
import com.blandev.lottery.backend.domain.exception.BusinessRuleException;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@Service
public class SellTicketsUseCase {

  private final SaleRepository saleRepository;
  private final CustomerRepository customerRepository;
  private final LotteryTicketRepository ticketRepository;

  public SellTicketsUseCase(SaleRepository saleRepository,
      CustomerRepository customerRepository,
      LotteryTicketRepository ticketRepository) {
    this.saleRepository = saleRepository;
    this.customerRepository = customerRepository;
    this.ticketRepository = ticketRepository;
  }

  @Transactional
  public SaleResponseDTO execute(SellTicketDTO dto) {
    validateCustomerExists(dto.customerId());

    List<LotteryTicket> tickets = processTickets(dto.ticketIds());

    Sale sale = new Sale(dto.customerId(), tickets);
    Sale savedSale = saleRepository.save(sale);

    return SaleMapper.toResponseDTO(savedSale);
  }

  private void validateCustomerExists(Long customerId) {
    if (customerRepository.findById(customerId).isEmpty()) {
      throw new ResourceNotFoundException("Customer", customerId);
    }
  }

  private LotteryTicket findAndValidateTicket(Long ticketId) {
    LotteryTicket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new ResourceNotFoundException("Lottery Ticket", ticketId));

    if (!ticket.isAvailable()) {
      throw new BusinessRuleException("Ticket " + ticket.getNumber() + " is not available for sale.");
    }

    return ticket;
  }

  private List<LotteryTicket> processTickets(List<Long> ticketIds) {
    List<LotteryTicket> tickets = new ArrayList<>();
    for (Long ticketId : ticketIds) {
      LotteryTicket ticket = findAndValidateTicket(ticketId);
      ticket.setAsSold();
      ticketRepository.save(ticket);
      tickets.add(ticket);
    }
    return tickets;
  }

}
