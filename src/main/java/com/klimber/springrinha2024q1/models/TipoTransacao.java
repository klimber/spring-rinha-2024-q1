package com.klimber.springrinha2024q1.models;

import java.util.function.Function;

public enum TipoTransacao {
    d(l -> -l),
    c(l -> l);

    private final Function<Long, Long> operation;

    TipoTransacao(Function<Long, Long> operation) {
        this.operation = operation;
    }

    public Long getValorForTipo(Long valor) {
        return this.operation.apply(valor);
    }
}
