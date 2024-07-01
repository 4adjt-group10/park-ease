package com.parkease.admin.domain;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.apllication.dto.PriceFormDTO;
import com.parkease.admin.infrastructure.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;
    public List<PriceDTO> findAll() {
        return priceRepository.findAll().stream().map(PriceDTO::new).toList();
    }

    public PriceDTO updated(Long id, PriceFormDTO formDTO) {
        return priceRepository.findById(id).map(PriceDTO::new).orElseThrow(EntityNotFoundException::new);

    }
}
