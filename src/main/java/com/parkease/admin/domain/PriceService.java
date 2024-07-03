package com.parkease.admin.domain;

import com.parkease.admin.apllication.PriceDTO;
import com.parkease.admin.apllication.PriceFormDTO;
import com.parkease.admin.infrastructure.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<PriceDTO> findAll() {
        return priceRepository.findAll().stream().map(PriceDTO::new).toList();
    }

    public PriceDTO findPriceById(Long id) {
        return priceRepository.findById(id).map(PriceDTO::new).orElseThrow(EntityNotFoundException::new);

    }

    public PriceDTO createNewPrice(PriceFormDTO formDTO) {
        Price price = priceRepository.save(new Price(formDTO));
        return new PriceDTO(price);
    }

    public PriceDTO updatePriceById(Long id, PriceFormDTO formDTO) {
        Price price = priceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        price.merge(formDTO);
        priceRepository.save(price);
        return new PriceDTO(price);
    }

    public void deletePrice(Long id) {
        if (!priceRepository.existsById(id)) {
            throw new EntityNotFoundException("Price with id " + id + " not found.");
        }
        priceRepository.deleteById(id);
    }
}
