package com.klimber.springrinha2024q1.repositories;

import com.klimber.springrinha2024q1.models.Transacao;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    @Query("SELECT * FROM transacao "
           + "WHERE cliente_id = :clientId "
           + "ORDER BY id DESC "
           + "LIMIT 10")
    Collection<Transacao> ultimasTransacoes(@Param("clientId") Long clienteId);

    @Query("SELECT * FROM transacao "
           + "WHERE cliente_id = :clientId "
           + "ORDER BY id DESC "
           + "LIMIT 1")
    Optional<Transacao> ultimaTransacao(@Param("clientId") Long clienteId);

    @Query("SELECT pg_advisory_xact_lock(:clientId)")
    Object lock(@Param("clientId") Long clienteId);

    @Query("SELECT pg_advisory_xact_lock_shared(:clientId)")
    Object lockShared(@Param("clientId") Long clienteId);

}
