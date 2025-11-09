package com.blandev.lottery.backend.application.usecase.sale;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.mapper.SaleMapper;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@Service
public class GetAllSalesUseCase {

    private final SaleRepository saleRepository;

    public GetAllSalesUseCase(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional(readOnly = true)
    public List<SaleResponseDTO> execute() {
        return saleRepository.findAll().stream()
                .map(SaleMapper::toResponseDTO)
                .toList();
    }

}
