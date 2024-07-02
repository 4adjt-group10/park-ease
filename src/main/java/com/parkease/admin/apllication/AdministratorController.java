package com.parkease.admin.apllication;

import com.parkease.admin.apllication.dto.PriceDTO;
import com.parkease.admin.apllication.dto.PriceFormDTO;
import com.parkease.admin.domain.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    PriceService priceService;

    @GetMapping("/listall")
    public ResponseEntity<List<PriceDTO>> findAllValues() {
        return ResponseEntity.ok(priceService.findAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PriceDTO> update(@PathVariable("id") Long id, @RequestBody PriceFormDTO formDTO) {
        return ResponseEntity.ok(priceService.updated(id,formDTO));
    }

}