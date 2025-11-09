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

import com.blandev.lottery.backend.application.dto.lottery.CreateLotteryDrawDTO;
import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.application.usecase.lottery.CreateLotteryDrawUseCase;
import com.blandev.lottery.backend.application.usecase.lottery.GetAllLotteryDrawsUseCase;
import com.blandev.lottery.backend.application.usecase.lottery.GetLotteryDrawByIdUseCase;
import com.blandev.lottery.backend.application.usecase.lottery.GetLotteryDrawByNameUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/lottery-draws")
public class LotteryDrawController {

    private final CreateLotteryDrawUseCase createLotteryDrawUseCase;
    private final GetAllLotteryDrawsUseCase getAllLotteryDrawsUseCase;
    private final GetLotteryDrawByIdUseCase getLotteryDrawByIdUseCase;
    private final GetLotteryDrawByNameUseCase getLotteryDrawByNameUseCase;

    public LotteryDrawController(
            CreateLotteryDrawUseCase createLotteryDrawUseCase,
            GetAllLotteryDrawsUseCase getAllLotteryDrawsUseCase,
            GetLotteryDrawByIdUseCase getLotteryDrawByIdUseCase,
            GetLotteryDrawByNameUseCase getLotteryDrawByNameUseCase) {
        this.createLotteryDrawUseCase = createLotteryDrawUseCase;
        this.getAllLotteryDrawsUseCase = getAllLotteryDrawsUseCase;
        this.getLotteryDrawByIdUseCase = getLotteryDrawByIdUseCase;
        this.getLotteryDrawByNameUseCase = getLotteryDrawByNameUseCase;
    }

    @PostMapping
    public ResponseEntity<LotteryDrawResponseDTO> createLotteryDraw(@Valid @RequestBody CreateLotteryDrawDTO request) {
        LotteryDrawResponseDTO response = createLotteryDrawUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LotteryDrawResponseDTO>> getAllLotteryDraws() {
        List<LotteryDrawResponseDTO> responses = getAllLotteryDrawsUseCase.execute();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotteryDrawResponseDTO> getLotteryDrawById(@PathVariable Long id) {
        LotteryDrawResponseDTO response = getLotteryDrawByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<LotteryDrawResponseDTO> getLotteryDrawByName(@PathVariable String name) {
        LotteryDrawResponseDTO response = getLotteryDrawByNameUseCase.execute(name);
        return ResponseEntity.ok(response);
    }

}
