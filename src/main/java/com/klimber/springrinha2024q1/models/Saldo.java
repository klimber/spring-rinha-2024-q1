package com.klimber.springrinha2024q1.models;

import com.klimber.springrinha2024q1.exceptions.LimiteInsuficienteException;
import org.springframework.data.annotation.Id;

public record Saldo(@Id Long id,
                    Long cliente_id,
                    Long saldo) {
    public Saldo atualizar(Transacao transacao, Cliente cliente) {
        if(this.saldo + cliente.limite() + transacao.getValorForTipo() < 0) {
            throw new LimiteInsuficienteException("Limite insuficiente para esta transação");
        }
        return new Saldo(this.id,
                         this.cliente_id,
                         this.saldo + transacao.getValorForTipo());
    }
}
