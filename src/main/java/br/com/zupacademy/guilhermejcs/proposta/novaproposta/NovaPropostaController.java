package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NovaPropostaRepository novaPropostaRepository;

    @PostMapping(value = "/propostas")
    @Transactional
    public ResponseEntity<?> criaProposta(
            @RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder builder) {

        Optional<Proposta> possivelProposta = novaPropostaRepository
                .findByDocumento(request.getDocumento());
        if (possivelProposta.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Já existe uma proposta para esse CPF/CNPJ");
        }
        Proposta novaProposta = request.toModel();
        manager.persist(novaProposta);

        URI enderecoConsulta = builder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(enderecoConsulta).build();
    }

}