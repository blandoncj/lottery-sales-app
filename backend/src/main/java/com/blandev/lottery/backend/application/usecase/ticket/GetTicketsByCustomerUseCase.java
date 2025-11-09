package com.blandev.lottery.backend.application.usecase.ticket;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.application.mapper.TicketMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@Service
public class GetTicketsByCustomerUseCase {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;

    public GetTicketsByCustomerUseCase(SaleRepository saleRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<TicketResponseDTO> execute(Long customerId) {
        validateCustomerExists(customerId);

        List<Sale> sales = saleRepository.findByCustomerId(customerId);

        List<LotteryTicket> tickets = sales.stream()
                .flatMap(sale -> sale.getTickets().stream())
                .toList();

        return tickets.stream()
                .map(TicketMapper::toResponseDTO)
                .toList();
    }

    private void validateCustomerExists(Long customerId) {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new ResourceNotFoundException("Customer", customerId);
        }

    }

}
