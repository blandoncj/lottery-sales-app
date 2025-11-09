package com.blandev.lottery.backend.application.usecase.sale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blandev.lottery.backend.application.dto.sale.SaleResponseDTO;
import com.blandev.lottery.backend.application.mapper.SaleMapper;
import com.blandev.lottery.backend.domain.exception.ResourceNotFoundException;
import com.blandev.lottery.backend.domain.repository.SaleRepository;

@Service
public class GetSaleByIdUseCase {

    private final SaleRepository saleRepository;

    public GetSaleByIdUseCase(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional(readOnly = true)
    public SaleResponseDTO execute(Long id) {
        return saleRepository.findById(id)
                .map(SaleMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", id));
    }

}
