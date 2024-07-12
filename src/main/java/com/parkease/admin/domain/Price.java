package com.parkease.admin.domain;

import com.parkease.admin.apllication.PriceFormDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private BigDecimal value;
    private String name;
    private boolean currentPrice = false;

    public Price() {
    }


    public Price(BigDecimal value, String name, boolean currentPrice) {
        this.value = value;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public Price(PriceFormDTO formDTO) {
        this(formDTO.value(), formDTO.name(), formDTO.currentPrice());
    }

    public void merge(PriceFormDTO formDTO){
        this.value = formDTO.value();
        this.name = formDTO.name();
        this.currentPrice = formDTO.currentPrice();
    }

    public boolean isCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(boolean currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal price) {
        this.value = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}