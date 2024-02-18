package com.klimber.springrinha2024q1.repositories;

import com.klimber.springrinha2024q1.models.Saldo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends CrudRepository<Saldo, Long> {

    @Lock(LockMode.PESSIMISTIC_WRITE)
    @Query("SELECT * FROM saldo WHERE cliente_id = :clienteId")
    Saldo findByClienteId(Long clienteId);
}
