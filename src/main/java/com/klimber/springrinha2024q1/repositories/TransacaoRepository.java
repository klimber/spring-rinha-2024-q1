package com.klimber.springrinha2024q1.repositories;

import com.klimber.springrinha2024q1.models.Transacao;
import java.util.Collection;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

    @Query("SELECT * FROM transacao "
           + "WHERE cliente_id = :clientId "
           + "ORDER BY realizada_em DESC "
           + "LIMIT 10")
    Collection<Transacao> ultimasTransacoes(@Param("clientId") Long clienteId);

}
