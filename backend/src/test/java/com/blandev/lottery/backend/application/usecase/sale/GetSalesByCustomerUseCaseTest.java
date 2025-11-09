package com.blandev.lottery.backend.application.usecase.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.Customer;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.model.Sale;
import com.blandev.lottery.backend.domain.model.TicketStatus;
import com.blandev.lottery.backend.domain.repository.CustomerRepository;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetSalesByCustomerUseCase Test")
class GetSalesByCustomerUseCaseTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GetSalesByCustomerUseCase getSalesByCustomerUseCase;

    private Customer customer;
    private List<Sale> sales;

    @BeforeEach
    void setUp() {
        customer = new Customer("123456", "John Doe", "john@email.com");
        customer.setId(1L);

        List<LotteryTicket> tickets1 = new ArrayList<>();
        tickets1.add(new LotteryTicket(1L, "000001", BigDecimal.valueOf(10.00), TicketStatus.AVAILABLE, 1L));
        Sale sale1 = new Sale(1L, 1L, tickets1, BigDecimal.valueOf(10.00), LocalDateTime.now());

        List<LotteryTicket> tickets2 = new ArrayList<>();
        tickets2.add(new LotteryTicket(2L, "000002", BigDecimal.valueOf(15.00), TicketStatus.AVAILABLE, 1L));
        tickets2.add(new LotteryTicket(3L, "000003", BigDecimal.valueOf(15.00), TicketStatus.AVAILABLE, 1L));
        Sale sale2 = new Sale(2L, 1L, tickets2, BigDecimal.valueOf(30.00), LocalDateTime.now());

        sales = Arrays.asList(sale1, sale2);
    }

    @Test
    @DisplayName("Should return all sales for a customer successfully")
    void shouldReturnAllSalesForCustomerSuccessfully() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(saleRepository.findByCustomerId(1L)).thenReturn(sales);

        List<SaleResponseDTO> result = getSalesByCustomerUseCase.execute(1L);

        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).id());
        assertEquals(1L, result.get(0).customerId());
        assertEquals(BigDecimal.valueOf(10.00), result.get(0).totalAmount());
        assertEquals(1, result.get(0).tickets().size());

        assertEquals(2L, result.get(1).id());
        assertEquals(1L, result.get(1).customerId());
        assertEquals(BigDecimal.valueOf(30.00), result.get(1).totalAmount());
        assertEquals(2, result.get(1).tickets().size());

        verify(customerRepository).findById(1L);
        verify(saleRepository).findByCustomerId(1L);
    }

    @Test
    @DisplayName("Should return empty list when customer has no sales")
    void shouldReturnEmptyListWhenCustomerHasNoSales() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(saleRepository.findByCustomerId(1L)).thenReturn(Collections.emptyList());

        List<SaleResponseDTO> result = getSalesByCustomerUseCase.execute(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());

        verify(customerRepository).findById(1L);
        verify(saleRepository).findByCustomerId(1L);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when customer does not exist")
    void shouldThrowResourceNotFoundExceptionWhenCustomerDoesNotExist() {
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> getSalesByCustomerUseCase.execute(999L));

        assertTrue(exception.getMessage().contains("Customer"));
        assertTrue(exception.getMessage().contains("999"));

        verify(customerRepository).findById(999L);
        verify(saleRepository, never()).findByCustomerId(anyLong());
    }
}