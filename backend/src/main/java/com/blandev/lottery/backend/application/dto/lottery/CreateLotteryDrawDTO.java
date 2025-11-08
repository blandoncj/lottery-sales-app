package com.blandev.lottery.backend.application.dto.lottery;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLotteryDrawDTO(
    @NotBlank(message = "Name is required") String name,
    @NotNull(message = "Draw date is required") @Future(message = "Draw date must be in the future") LocalDateTime drawDate) {
}
