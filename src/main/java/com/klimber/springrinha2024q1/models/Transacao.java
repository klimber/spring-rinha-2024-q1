package com.klimber.springrinha2024q1.models;

import java.time.Instant;
import org.springframework.data.annotation.Id;

public record Transacao(@Id Long id,
                        Long clienteId,
                        TipoTransacao tipo,
                        Long valor,
                        String descricao,
                        Instant realizadaEm,
                        Long saldo) {

    public Long getDiferenca() {
        return this.tipo.valorForTipo(this.valor);
    }
}
