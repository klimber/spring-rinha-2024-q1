package com.klimber.springrinha2024q1.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.klimber.springrinha2024q1.models.Cliente;
import com.klimber.springrinha2024q1.models.Saldo;
import com.klimber.springrinha2024q1.models.TipoTransacao;
import com.klimber.springrinha2024q1.models.Transacao;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public record GetExtratoResponse(SaldoDto saldo,
                                 @JsonProperty("ultimas_transacoes") Collection<TransacaoDto> ultimasTransacoes) {

    public static GetExtratoResponse from(Cliente cliente,
                                          Saldo saldo,
                                          Collection<Transacao> transacoes) {
        SaldoDto saldoDto = SaldoDto.fromCliente(cliente, saldo);
        List<TransacaoDto> ultimasTransacoes = transacoes.stream()
                                                         .map(TransacaoDto::fromTransacao)
                                                         .collect(Collectors.toList());
        return new GetExtratoResponse(saldoDto, ultimasTransacoes);
    }

    private record SaldoDto(Long total,
                            Long limite,
                            @JsonProperty("data_extrato") Instant dataExtrato) {
        public static SaldoDto fromCliente(Cliente cliente, Saldo saldo) {
            return new SaldoDto(saldo.saldo(), cliente.limite(), Instant.now());
        }
    }

    private record TransacaoDto(Long valor,
                                TipoTransacao tipo,
                                String descricao,
                                @JsonProperty("realizada_em") Instant realizadaEm) {
        public static TransacaoDto fromTransacao(Transacao transacao) {
            return new TransacaoDto(transacao.valor(),
                                    transacao.tipo(),
                                    transacao.descricao(),
                                    transacao.realizadaEm());
        }
    }
}
