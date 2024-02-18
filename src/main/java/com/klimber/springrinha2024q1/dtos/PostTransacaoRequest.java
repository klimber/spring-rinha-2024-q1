package com.klimber.springrinha2024q1.dtos;

import com.klimber.springrinha2024q1.models.TipoTransacao;
import com.klimber.springrinha2024q1.models.Transacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public record PostTransacaoRequest(@NotNull TipoTransacao tipo,
                                   @Positive
                                   @NotNull
                                   Long valor,
                                   @Size(min = 1, max = 10)
                                   @NotNull
                                   String descricao) {

    public Transacao toTransacao(Long clienteId) {
        return new Transacao(null,
                             clienteId,
                             this.tipo,
                             this.valor,
                             this.descricao,
                             Instant.now());
    }
}
