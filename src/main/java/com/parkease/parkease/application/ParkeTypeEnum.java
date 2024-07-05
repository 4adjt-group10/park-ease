package com.parkease.parkease.application;

public enum ParkeTypeEnum {
    FIXEDTIME("Fixo"),
    VARIABLETIME("Variável");

    private final String descricao;

    ParkeTypeEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
