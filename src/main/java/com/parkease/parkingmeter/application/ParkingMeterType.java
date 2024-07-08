package com.parkease.parkingmeter.application;

public enum ParkingMeterType {

    FIXED_TIME("Fixo"), VARIABLE_TIME("Variável");

    private final String descricao;

    ParkingMeterType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
