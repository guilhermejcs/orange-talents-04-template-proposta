package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class NovaPropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private NovaPropostaRepository novaPropostaRepository;

    @PostMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<?>  consulta(@PathVariable("id") Long id){
        Optional<Proposta> proposta = novaPropostaRepository.findById(id);
        if(proposta.isPresent()){
            ConsultaPropostaResponse dto = new ConsultaPropostaResponse();
            dto.atribuirDados(proposta.get());
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A proposta com Id "+ id + " não existe.");
        }
    }
}