package com.klimber.springrinha2024q1.repositories;

import com.klimber.springrinha2024q1.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
