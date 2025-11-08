package com.blandev.lottery.backend.application.dto.lottery;

import java.time.LocalDateTime;

public record LotteryDrawResponseDTO(
        Long id,
        String name,
        LocalDateTime drawDate) {
}
