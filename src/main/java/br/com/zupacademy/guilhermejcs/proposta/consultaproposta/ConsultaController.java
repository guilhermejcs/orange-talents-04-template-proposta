package br.com.zupacademy.guilhermejcs.proposta.consultaproposta;

import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Avaliacao;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/consultas")
public class ConsultaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ConsultaClient consultaClient;

    @PostMapping
    @Transactional
    public ResponseEntity<?>
    consulta(@RequestBody @Valid ConsultaResquest request) {

        Consulta consultaReq = request.toModel();
        Consulta consultaResp = null;

        Proposta proposta = manager.find(Proposta.class, Long.parseLong(request.idProposta));

        try {
            consultaResp = consultaClient.consulta(consultaReq);
            proposta.setStatus(Avaliacao.ELEGIVEL);
            manager.merge(proposta);
            return ResponseEntity.status(HttpStatus.OK).body(consultaResp);
        } catch (FeignException.UnprocessableEntity e) {
            proposta.setStatus(Avaliacao.NAO_ELEGIVEL);
            manager.merge(proposta);
            return ResponseEntity.status(HttpStatus.OK).body(e.contentUTF8());
        }
    }
}
