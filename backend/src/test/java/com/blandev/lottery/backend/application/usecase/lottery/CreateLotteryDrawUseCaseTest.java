package com.blandev.lottery.backend.application.usecase.lottery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.blandev.lottery.backend.application.dto.lottery.CreateLotteryDrawDTO;
import com.blandev.lottery.backend.application.dto.lottery.LotteryDrawResponseDTO;
import com.blandev.lottery.backend.domain.exception.DuplicateResourceException;
import com.blandev.lottery.backend.domain.model.LotteryDraw;
import com.blandev.lottery.backend.domain.repository.LotteryDrawRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateLotteryDrawUseCase Test")
class CreateLotteryDrawUseCaseTest {

    @Mock
    private LotteryDrawRepository lotteryDrawRepository;

    @InjectMocks
    private CreateLotteryDrawUseCase createLotteryDrawUseCase;

    private CreateLotteryDrawDTO validDTO;
    private LotteryDraw savedDraw;
    private LocalDateTime drawDate;

    @BeforeEach
    void setUp() {
        drawDate = LocalDateTime.of(2025, 12, 31, 20, 0);
        validDTO = new CreateLotteryDrawDTO("Lotería de Medellín", drawDate);

        savedDraw = new LotteryDraw("Lotería de Medellín", drawDate);
        savedDraw.setId(1L);
    }

    @Test
    @DisplayName("Should create a new lottery draw successfully")
    void shouldCreateNewLotteryDrawSuccessfully() {
        when(lotteryDrawRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(lotteryDrawRepository.save(any(LotteryDraw.class))).thenReturn(savedDraw);

        LotteryDrawResponseDTO result = createLotteryDrawUseCase.execute(validDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Lotería de Medellín", result.name());
        assertEquals(drawDate, result.drawDate());

        verify(lotteryDrawRepository).findByName("Lotería de Medellín");
        verify(lotteryDrawRepository).save(any(LotteryDraw.class));
    }

    @Test
    @DisplayName("Should throw DuplicateResourceException when lottery draw name already exists")
    void shouldThrowDuplicateResourceExceptionWhenNameExists() {
        LotteryDraw existingDraw = new LotteryDraw("Lotería de Medellín", drawDate);
        existingDraw.setId(2L);
        when(lotteryDrawRepository.findByName(validDTO.name())).thenReturn(Optional.of(existingDraw));

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> createLotteryDrawUseCase.execute(validDTO));

        assertTrue(exception.getMessage().contains("Lottery draw"));
        assertTrue(exception.getMessage().contains("name"));
        assertTrue(exception.getMessage().contains("Lotería de Medellín"));

        verify(lotteryDrawRepository).findByName("Lotería de Medellín");
        verify(lotteryDrawRepository, never()).save(any(LotteryDraw.class));
    }

    @Test
    @DisplayName("Should create lottery draw with different names")
    void shouldCreateLotteryDrawWithDifferentNames() {
        CreateLotteryDrawDTO anotherDTO = new CreateLotteryDrawDTO(
                "Lotería del Quindío",
                LocalDateTime.of(2026, 1, 1, 0, 0));

        LotteryDraw anotherSavedDraw = new LotteryDraw(
                "Lotería del Quindío",
                LocalDateTime.of(2026, 1, 1, 0, 0));
        anotherSavedDraw.setId(2L);

        when(lotteryDrawRepository.findByName("Lotería del Quindío")).thenReturn(Optional.empty());
        when(lotteryDrawRepository.save(any(LotteryDraw.class))).thenReturn(anotherSavedDraw);

        LotteryDrawResponseDTO result = createLotteryDrawUseCase.execute(anotherDTO);

        assertNotNull(result);
        assertEquals(2L, result.id());
        assertEquals("Lotería del Quindío", result.name());

        verify(lotteryDrawRepository).findByName("Lotería del Quindío");
        verify(lotteryDrawRepository).save(any(LotteryDraw.class));
    }

    @Test
    @DisplayName("Should preserve draw date when creating lottery draw")
    void shouldPreserveDrawDateWhenCreatingLotteryDraw() {
        LocalDateTime specificDate = LocalDateTime.of(2025, 6, 15, 18, 30);
        CreateLotteryDrawDTO dtoWithSpecificDate = new CreateLotteryDrawDTO(
                "Lotería de Boyacá",
                specificDate);

        LotteryDraw drawWithSpecificDate = new LotteryDraw("Lotería de Boyacá", specificDate);
        drawWithSpecificDate.setId(3L);

        when(lotteryDrawRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(lotteryDrawRepository.save(any(LotteryDraw.class))).thenReturn(drawWithSpecificDate);

        LotteryDrawResponseDTO result = createLotteryDrawUseCase.execute(dtoWithSpecificDate);

        assertNotNull(result);
        assertEquals(specificDate, result.drawDate());
        assertEquals("Lotería de Boyacá", result.name());

        verify(lotteryDrawRepository).findByName("Lotería de Boyacá");
        verify(lotteryDrawRepository).save(any(LotteryDraw.class));
    }
}