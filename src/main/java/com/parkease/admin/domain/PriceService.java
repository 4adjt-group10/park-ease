package com.parkease.admin.domain;

import com.parkease.admin.apllication.PriceDTO;
import com.parkease.admin.apllication.PriceFormDTO;
import com.parkease.admin.infrastructure.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PriceDTO createNewPrice(PriceFormDTO formDTO) throws IllegalStateException {
        if(formDTO.currentPrice()){
            Optional<Price> currentPrice = priceRepository.findByCurrentPriceTrue();
            if(currentPrice.isPresent()){
                throw new IllegalStateException("Current price already exists");
            }
        }
        Price price = priceRepository.save(new Price(formDTO));
        return new PriceDTO(price);
    }

    public PriceDTO updatePriceById(Long id, PriceFormDTO formDTO) {
        Price price = priceRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if(formDTO.currentPrice()){
            Optional<Price> currentPrice = priceRepository.findByCurrentPriceTrue();
            if(currentPrice.isPresent() && !currentPrice.get().getId().equals(id)){
                currentPrice.get().setCurrentPrice(false);
                priceRepository.save(currentPrice.get());
            }
        }
        price.merge(formDTO);
        priceRepository.save(price);
        return new PriceDTO(price);
    }

    public void deletePrice(Long id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price with id " + id + " not found."));

        if (price.isCurrentPrice()) {
            throw new IllegalArgumentException("Cannot delete the current price.");
        }

        priceRepository.deleteById(id);
    }

    public PriceDTO findCurrentPrice(){
        return priceRepository.findByCurrentPriceTrue().map(PriceDTO::new).orElseThrow(() ->
                new EntityNotFoundException("Not found any current price"));
    }
}
