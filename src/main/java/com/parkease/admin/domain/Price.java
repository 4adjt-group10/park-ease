package com.parkease.admin.domain;

import com.parkease.admin.apllication.dto.PriceFormDTO;
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

    public Price() {
    }

    public Price(BigDecimal value, String name) {
        this.value = value;
        this.name = name;
    }

    public Price(PriceFormDTO formDTO) {
        this(formDTO.price(), formDTO.name());
    }

    public void merge(PriceFormDTO formDTO){
        this.value = formDTO.price();
        this.name = formDTO.name();
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
