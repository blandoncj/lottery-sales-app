package com.blandev.lottery.backend.application.usecase.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blandev.lottery.backend.application.dto.ticket.GenerateTicketsDTO;
import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.model.LotteryTicket;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;
import com.blandev.lottery.backend.domain.repository.LotteryTicketRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("GenerateTicketsUseCase Test")
class GenerateTicketsUseCaseTest {

    @Mock
    private LotteryTicketRepository ticketRepository;

    @Mock
    private LotteryDrawRepository drawRepository;

    @InjectMocks
    private GenerateTicketsUseCase generateTicketsUseCase;

    private GenerateTicketsDTO validDTO;
    private LotteryDraw lotteryDraw;

    @BeforeEach
    void setUp() {
        validDTO = new GenerateTicketsDTO(1L, 10, BigDecimal.valueOf(5.00));

        lotteryDraw = new LotteryDraw("Lotería de Navidad", LocalDateTime.of(2025, 12, 25, 20, 0));
        lotteryDraw.setId(1L);
    }

    @Test
    @DisplayName("Should generate tickets successfully")
    void shouldGenerateTicketsSuccessfully() {
        when(drawRepository.findById(1L)).thenReturn(Optional.of(lotteryDraw));

        List<LotteryTicket> savedTickets = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            LotteryTicket ticket = new LotteryTicket(
                    String.format("%06d", i),
                    BigDecimal.valueOf(5.00),
                    1L);
            ticket.setId((long) i);
            savedTickets.add(ticket);
        }

        when(ticketRepository.saveAll(anyList())).thenReturn(savedTickets);

        List<TicketResponseDTO> result = generateTicketsUseCase.execute(validDTO);

        assertNotNull(result);
        assertEquals(10, result.size());

        assertEquals(1L, result.get(0).id());
        assertEquals("000001", result.get(0).number());
        assertEquals(BigDecimal.valueOf(5.00), result.get(0).price());

        assertEquals(10L, result.get(9).id());
        assertEquals("000010", result.get(9).number());

        verify(drawRepository).findById(1L);
        verify(ticketRepository).saveAll(anyList());
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when draw does not exist")
    void shouldThrowResourceNotFoundExceptionWhenDrawDoesNotExist() {
        when(drawRepository.findById(999L)).thenReturn(Optional.empty());
        GenerateTicketsDTO dtoWithInvalidDraw = new GenerateTicketsDTO(999L, 5, BigDecimal.valueOf(5.00));

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> generateTicketsUseCase.execute(dtoWithInvalidDraw));

        assertTrue(exception.getMessage().contains("Lottery draw"));
        assertTrue(exception.getMessage().contains("999"));

        verify(drawRepository).findById(999L);
        verify(ticketRepository, never()).saveAll(anyList());
    }

    @Test
    @DisplayName("Should generate large number of tickets")
    void shouldGenerateLargeNumberOfTickets() {
        int largeQuantity = 100;
        when(drawRepository.findById(1L)).thenReturn(Optional.of(lotteryDraw));

        List<LotteryTicket> savedTickets = new ArrayList<>();
        for (int i = 1; i <= largeQuantity; i++) {
            LotteryTicket ticket = new LotteryTicket(
                    String.format("%06d", i),
                    BigDecimal.valueOf(5.00),
                    1L);
            ticket.setId((long) i);
            savedTickets.add(ticket);
        }

        when(ticketRepository.saveAll(anyList())).thenReturn(savedTickets);

        GenerateTicketsDTO largeDTO = new GenerateTicketsDTO(1L, largeQuantity, BigDecimal.valueOf(5.00));

        List<TicketResponseDTO> result = generateTicketsUseCase.execute(largeDTO);

        assertNotNull(result);
        assertEquals(largeQuantity, result.size());
        assertEquals("000001", result.get(0).number());
        assertEquals("000100", result.get(99).number());

        verify(drawRepository).findById(1L);
        verify(ticketRepository).saveAll(anyList());
    }

}