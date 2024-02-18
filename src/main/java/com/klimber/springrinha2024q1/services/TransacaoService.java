package com.klimber.springrinha2024q1.services;

import com.klimber.springrinha2024q1.models.Transacao;
import com.klimber.springrinha2024q1.repositories.TransacaoRepository;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    private final TransacaoRepository rep;

    public TransacaoService(TransacaoRepository rep) {
        this.rep = rep;
    }

    public Transacao save(Transacao transacao) {
        return rep.save(transacao);
    }

    public Collection<Transacao> ultimasTransacoes(Long clienteId) {
        return rep.ultimasTransacoes(clienteId);
    }
}
