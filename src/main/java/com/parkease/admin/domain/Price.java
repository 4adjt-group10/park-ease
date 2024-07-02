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
    private BigDecimal price;
    private String name;

    public Price() {
    }


    public Price(BigDecimal price,  String name) {
        this.price = price;
        this.name = name;
    }

    public Price(PriceFormDTO formDTO) {
        this(formDTO.price(), formDTO.name());
    }

    public void merge(PriceFormDTO formDTO){
        this.price = formDTO.price();
        this.name = formDTO.name();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
