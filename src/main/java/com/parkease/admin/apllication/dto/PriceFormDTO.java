package com.parkease.admin.apllication.dto;

import com.parkease.admin.domain.Price;

import java.math.BigDecimal;

public record PriceFormDTO( BigDecimal value,  String name, boolean currentPrice) {
    public PriceFormDTO(Price price){
        this(   price.getValue(),
                price.getName(),
                price.isCurrentPrice());
    }
}
