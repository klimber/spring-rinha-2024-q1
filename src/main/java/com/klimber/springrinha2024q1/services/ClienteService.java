package com.klimber.springrinha2024q1.services;

import com.klimber.springrinha2024q1.exceptions.ClienteNotFoundException;
import com.klimber.springrinha2024q1.models.Cliente;
import com.klimber.springrinha2024q1.repositories.ClienteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository rep;

    public ClienteService(ClienteRepository rep) {
        this.rep = rep;
    }

    @Cacheable("clienteCache")
    public Cliente findById(Long clienteId) {
        return rep.findById(clienteId)
                  .orElseThrow(() -> {
                      String message = String.format("Unable to find cliente with id '%d'", clienteId);
                      return new ClienteNotFoundException(message);
                  });
    }
}
