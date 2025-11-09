package com.blandev.lottery.backend.application.usecase.lottery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetAllLotteryDrawsUseCase Test")
class GetAllLotteryDrawsUseCaseTest {

    @Mock
    private LotteryDrawRepository lotteryDrawRepository;

    @InjectMocks
    private GetAllLotteryDrawsUseCase getAllLotteryDrawsUseCase;

    private List<LotteryDraw> lotteryDraws;

    @BeforeEach
    void setUp() {
        LotteryDraw draw1 = new LotteryDraw("Lotería de Medellín", LocalDateTime.of(2025, 12, 25, 20, 0));
        draw1.setId(1L);

        LotteryDraw draw2 = new LotteryDraw("Lotería del Quindío", LocalDateTime.of(2026, 1, 1, 0, 0));
        draw2.setId(2L);

        LotteryDraw draw3 = new LotteryDraw("Lotería de Boyacá", LocalDateTime.of(2025, 6, 15, 18, 0));
        draw3.setId(3L);

        lotteryDraws = Arrays.asList(draw1, draw2, draw3);
    }

    @Test
    @DisplayName("Should return all lottery draws successfully")
    void shouldReturnAllLotteryDrawsSuccessfully() {
        when(lotteryDrawRepository.findAll()).thenReturn(lotteryDraws);

        List<LotteryDrawResponseDTO> result = getAllLotteryDrawsUseCase.execute();

        assertNotNull(result);
        assertEquals(3, result.size());

        assertEquals(1L, result.get(0).id());
        assertEquals("Lotería de Medellín", result.get(0).name());
        assertEquals(LocalDateTime.of(2025, 12, 25, 20, 0), result.get(0).drawDate());

        assertEquals(2L, result.get(1).id());
        assertEquals("Lotería del Quindío", result.get(1).name());

        assertEquals(3L, result.get(2).id());
        assertEquals("Lotería de Boyacá", result.get(2).name());

        verify(lotteryDrawRepository).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no lottery draws exist")
    void shouldReturnEmptyListWhenNoLotteryDrawsExist() {
        when(lotteryDrawRepository.findAll()).thenReturn(Collections.emptyList());

        List<LotteryDrawResponseDTO> result = getAllLotteryDrawsUseCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());

        verify(lotteryDrawRepository).findAll();
    }

}