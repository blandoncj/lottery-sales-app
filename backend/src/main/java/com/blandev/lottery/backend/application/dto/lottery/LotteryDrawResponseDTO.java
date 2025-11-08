package com.blandev.lottery.backend.application.dto.lottery;

import java.time.LocalDateTime;

public record LotteryDrawReponseDTO(
    Long id,
    String name,
    LocalDateTime drawDate) {
}
