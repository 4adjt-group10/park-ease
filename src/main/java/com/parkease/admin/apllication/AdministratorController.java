package com.parkease.admin.apllication;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.apllication.dto.PriceFormDTO;
import com.parkease.admin.domain.PriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/price")
public class AdministratorController {
    @Autowired
    PriceService priceService;

    @GetMapping("/listall")
    public ResponseEntity<List<PriceDTO>> findAllValues() {
        return ResponseEntity.ok(priceService.findAll());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<PriceDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(priceService.findPriceById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PriceDTO> create(@RequestBody @Valid PriceFormDTO formDTO) {
        return ResponseEntity.ok(priceService.createNewPrice(formDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PriceDTO> update(@PathVariable("id") Long id, @RequestBody @Valid PriceFormDTO formDTO) {
        return ResponseEntity.ok(priceService.updatePriceById(id, formDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        priceService.deletePrice(id);
        return ResponseEntity.noContent().build();

    }

}