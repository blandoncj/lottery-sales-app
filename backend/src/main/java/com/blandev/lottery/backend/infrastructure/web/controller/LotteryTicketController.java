package com.blandev.lottery.backend.infrastructure.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.dto.sale.SellTicketDTO;
import com.blandev.lottery.backend.application.dto.ticket.GenerateTicketsDTO;
import com.blandev.lottery.backend.application.dto.ticket.TicketResponseDTO;
import com.blandev.lottery.backend.application.usecase.ticket.GenerateTicketsUseCase;
import com.blandev.lottery.backend.application.usecase.ticket.GetTicketsByCustomerUseCase;
import com.blandev.lottery.backend.application.usecase.ticket.GetTicketsByLotteryDrawUseCase;
import com.blandev.lottery.backend.application.usecase.ticket.SellTicketsUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lottery-tickets")
public class LotteryTicketController {

    private final GenerateTicketsUseCase generateTicketsUseCase;
    private final SellTicketsUseCase sellTicketsUseCase;
    private final GetTicketsByLotteryDrawUseCase getTicketsByLotteryDrawUseCase;
    private final GetTicketsByCustomerUseCase getTicketsByCustomerUseCase;

    public LotteryTicketController(GenerateTicketsUseCase generateTicketsUseCase,
            SellTicketsUseCase sellTicketsUseCase,
            GetTicketsByLotteryDrawUseCase getTicketsByLotteryDrawUseCase,
            GetTicketsByCustomerUseCase getTicketsByCustomerUseCase) {
        this.generateTicketsUseCase = generateTicketsUseCase;
        this.sellTicketsUseCase = sellTicketsUseCase;
        this.getTicketsByLotteryDrawUseCase = getTicketsByLotteryDrawUseCase;
        this.getTicketsByCustomerUseCase = getTicketsByCustomerUseCase;
    }

    @PostMapping("/generate")
    public ResponseEntity<List<TicketResponseDTO>> generateTickets(@Valid @RequestBody GenerateTicketsDTO request) {
        List<TicketResponseDTO> tickets = generateTicketsUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tickets);
    }

    @PostMapping("/sell")
    public ResponseEntity<SaleResponseDTO> sellTickets(@Valid @RequestBody SellTicketDTO request) {
        SaleResponseDTO sale = sellTicketsUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @GetMapping("/draw/{drawId}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByLotteryDraw(@PathVariable Long drawId) {
        List<TicketResponseDTO> tickets = getTicketsByLotteryDrawUseCase.execute(drawId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TicketResponseDTO>> getTicketsByCustomer(@PathVariable Long customerId) {
        List<TicketResponseDTO> tickets = getTicketsByCustomerUseCase.execute(customerId);
        return ResponseEntity.ok(tickets);
    }

}
