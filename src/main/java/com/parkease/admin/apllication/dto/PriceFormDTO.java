package com.parkease.admin.apllication.dto;

import com.parkease.admin.domain.Price;

import java.math.BigDecimal;

public record PriceFormDTO( BigDecimal price,  String name, boolean currentPrice) {
    public PriceFormDTO(Price price){
        this(   price.getPrice(),
                price.getName(),
                price.isCurrentPrice());
    }
}
