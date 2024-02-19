package com.klimber.springrinha2024q1.services;

import com.klimber.springrinha2024q1.exceptions.LimiteInsuficienteException;
import com.klimber.springrinha2024q1.models.Cliente;
import com.klimber.springrinha2024q1.models.Transacao;
import com.klimber.springrinha2024q1.repositories.TransacaoRepository;
import java.time.Instant;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    private final TransacaoRepository rep;
    private final ClienteService clienteService;

    public TransacaoService(TransacaoRepository rep, ClienteService clienteService) {
        this.rep = rep;
        this.clienteService = clienteService;
    }

    public Transacao save(Cliente cliente, Transacao transacao) {
        this.rep.lock(cliente.id());
        Long saldoAnterior = this.rep.ultimaTransacao(cliente.id())
                                     .map(Transacao::saldo)
                                     .orElse(0L);
        Long diferenca = transacao.getDiferenca();
        long novoSaldo = saldoAnterior + diferenca;
        if (novoSaldo + cliente.limite() < 0) {
            throw new LimiteInsuficienteException("Limite insuficiente para esta transação");
        }
        Transacao nova = new Transacao(null,
                                       transacao.clienteId(),
                                       transacao.tipo(),
                                       transacao.valor(),
                                       transacao.descricao(),
                                       Instant.now(),
                                       novoSaldo);
        return this.rep.save(nova);
    }

    public Collection<Transacao> ultimasTransacoes(Long clienteId) {
        this.rep.lockShared(clienteId);
        return rep.ultimasTransacoes(clienteId);
    }
}
