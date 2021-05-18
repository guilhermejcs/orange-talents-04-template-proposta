package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import br.com.zupacademy.guilhermejcs.proposta.compartilhado.ExecutorTransacao;
import br.com.zupacademy.guilhermejcs.proposta.criabiometria.CartaoRepository;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.NovaPropostaRepository;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class AssociaCarteiraController {

    private static final Logger log = LoggerFactory.getLogger(AssociaCarteiraController.class);

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    NovaPropostaRepository novaPropostaRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    AssociaCarteiraClient associaCarteiraClient;

    @Autowired
    ExecutorTransacao executorTransacao;

    @GetMapping(value = "/cartoes/{id}/carteira-digital")
    public ResponseEntity<?> associaCarteiraDigital(@PathVariable("id") Long id,
                                                    @RequestParam(value = "carteira", required = true) CarteiraDigital carteiraDigital,
                                                    UriComponentsBuilder uriComponentsBuilder) {

        Cartao cartao = cartaoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (carteiraRepository.existsByCartaoAndCarteiraDigital(cartao, carteiraDigital)) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Proposta proposta = novaPropostaRepository.findByCartao(cartao).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String email = proposta.getEmail();
        AssociaCarteiraRequest request = new AssociaCarteiraRequest(email, carteiraDigital.toString());

        AssociaCarteiraResponse respostaAssociaCarteira = null;
        try {
            respostaAssociaCarteira = associaCarteiraClient.carteira(cartao.getNumeroCartao(), request);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        Carteira novaCarteira = new Carteira(cartao, carteiraDigital, respostaAssociaCarteira.id);
        executorTransacao.salvaEComita(novaCarteira);

        String carteira = novaCarteira.getCarteiraDigital();
        String idCarteira = novaCarteira.getIdCarteira();
        String idCarteiraOfuscado = idCarteira.substring(0, 8) + "-****-" + idCarteira.substring(14, 18) + "-****-" + idCarteira.substring(24, 36);

        log.info("Carteira {} criada com o identificador {}", carteira, idCarteiraOfuscado);

        URI uri = uriComponentsBuilder.path("/api/cartoes/{id}/carteiras/{carteiraDigital}/{idCarteira}")
                .build(id, carteira, idCarteira);
        return ResponseEntity.created(uri).body("Carteira " + carteira + "Criado com o id " + idCarteira);
    }
}
