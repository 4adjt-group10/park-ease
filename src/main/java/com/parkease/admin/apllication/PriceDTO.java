package com.parkease.admin.apllication;

import com.parkease.admin.domain.Price;

import java.math.BigDecimal;


public record PriceDTO(Long id, BigDecimal value, String name, boolean currentPrice) {
    public PriceDTO(Price price){
        this(price.getId(),
                price.getValue(),
                price.getName(),
                price.isCurrentPrice());
    }

}
