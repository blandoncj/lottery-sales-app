package com.blandev.lottery.backend.application.mapper;

import org.springframework.stereotype.Component;

import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.domain.model.LotteryDraw;

@Component
public class LotteryDrawMapper {

    public LotteryDrawResponseDTO toResponseDTO(LotteryDraw draw) {
        return new LotteryDrawResponseDTO(
                draw.getId(),
                draw.getName(),
                draw.getDrawDate());
    }

}
