package com.klimber.springrinha2024q1.services;

import com.klimber.springrinha2024q1.models.Saldo;
import com.klimber.springrinha2024q1.repositories.SaldoRepository;
import org.springframework.stereotype.Service;

@Service
public class SaldoService {
    private final SaldoRepository rep;

    public SaldoService(SaldoRepository rep) {
        this.rep = rep;
    }

    public Saldo findByClienteId(Long clienteId) {
        return this.rep.findByClienteId(clienteId);
    }
    public Saldo save(Saldo saldo) {
        return this.rep.save(saldo);
    }
}
