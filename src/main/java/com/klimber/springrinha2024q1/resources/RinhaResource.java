package com.klimber.springrinha2024q1.resources;

import com.klimber.springrinha2024q1.dtos.GetExtratoResponse;
import com.klimber.springrinha2024q1.dtos.PostTransacaoRequest;
import com.klimber.springrinha2024q1.dtos.PostTransacaoResponse;
import com.klimber.springrinha2024q1.exceptions.ClienteNotFoundException;
import com.klimber.springrinha2024q1.exceptions.LimiteInsuficienteException;
import com.klimber.springrinha2024q1.models.Cliente;
import com.klimber.springrinha2024q1.models.Transacao;
import com.klimber.springrinha2024q1.services.ClienteService;
import com.klimber.springrinha2024q1.services.TransacaoService;
import jakarta.validation.Valid;
import java.util.Collection;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RinhaResource {
    private final ClienteService clienteService;
    private final TransacaoService transacaoService;

    public RinhaResource(ClienteService clienteService, TransacaoService transacaoService) {
        this.clienteService = clienteService;
        this.transacaoService = transacaoService;
    }

    @Transactional
    @PostMapping("/clientes/{id}/transacoes")
    public ResponseEntity<PostTransacaoResponse> postTransacao(@PathVariable("id") Long clienteId,
                                                               @RequestBody @Valid PostTransacaoRequest request) {
        Cliente cliente = this.clienteService.findById(clienteId);
        Transacao transacao = request.toTransacao(clienteId);
        Transacao salva = this.transacaoService.save(cliente, transacao);
        PostTransacaoResponse response = new PostTransacaoResponse(cliente.limite(), salva.saldo());
        return ResponseEntity.ok(response);
    }

    @Transactional
    @GetMapping("/clientes/{id}/extrato")
    public ResponseEntity<GetExtratoResponse> getExtrato(@PathVariable("id") Long clienteId) {
        Cliente cliente = this.clienteService.findById(clienteId);
        Collection<Transacao> ultimasTransacoes = this.transacaoService.ultimasTransacoes(cliente.id());
        Long saldo = ultimasTransacoes.stream().map(Transacao::saldo).findFirst().orElse(0L);
        GetExtratoResponse response = GetExtratoResponse.from(cliente, saldo, ultimasTransacoes);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleClienteNotFound(ClienteNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler({LimiteInsuficienteException.class,
                       HttpMessageNotReadableException.class,
                       MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleUnprocessable(Exception ex) {
        return ResponseEntity.status(422).body(Map.of("error", ex.getMessage()));
    }
}
