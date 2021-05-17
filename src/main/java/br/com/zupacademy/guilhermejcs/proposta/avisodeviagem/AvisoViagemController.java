package br.com.zupacademy.guilhermejcs.proposta.avisodeviagem;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import br.com.zupacademy.guilhermejcs.proposta.compartilhado.ExecutorTransacao;
import br.com.zupacademy.guilhermejcs.proposta.criabiometria.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class AvisoViagemController {

    private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    AvisoViagemClient avisoViagemClient;

    @Autowired
    private ExecutorTransacao transacao;

    @PostMapping(value = "/cartoes/{id}/aviso-viagem")
    public ResponseEntity<?> avisoViagem(@PathVariable("id") Long id,
                                         @RequestBody NovoAvisoViagemRequest request,
                                         @RequestHeader(HttpHeaders.USER_AGENT) String navegador,
                                         HttpServletRequest httpRequest) {

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AvisoViagem avisoViagem = new AvisoViagem(cartao, request.getDestino(), request.getDataTermino(), navegador, httpRequest.getRemoteAddr());

        NovoAvisoViagemResponse avisoStatus = null;

        try {
            avisoStatus = avisoViagemClient.statusAviso(cartao.getNumeroCartao(),request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if(!avisoStatus.getResultado().equals("CRIADO")){
            return ResponseEntity.badRequest().build();
        }

        try {
            transacao.salvaEComita(avisoViagem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        String cartaoRetorno = cartao.getNumeroCartao().substring(0, 4) + "-****-****-" + cartao.getNumeroCartao().substring(15, 19);
        String idCartaoRetorno = avisoViagem.getId().get().toString();

        logger.info("Aviso de viagem com o id {} criado para o cartão {}", idCartaoRetorno, cartaoRetorno);

        return ResponseEntity.ok().body("Aviso de viagem com o id "+idCartaoRetorno+" para o cartão "+cartaoRetorno);
    }
}
