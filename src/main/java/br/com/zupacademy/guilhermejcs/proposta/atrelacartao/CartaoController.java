package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Avaliacao;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.NovaPropostaRepository;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CartaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    NovaPropostaRepository novaPropostaRepository;

    @Autowired
    CartaoClient cartaoClient;

    @GetMapping(value = "/cartao")
    @Transactional
    @Scheduled(fixedDelayString = "${periodicidade.atrela-cartao}")
    public ResponseEntity<?> atrelaCartao() {
        List<Proposta> propostas = novaPropostaRepository.findByCartaoAndStatus(null, Avaliacao.ELEGIVEL);

        List<String> atualizadas = new ArrayList<>();
        List<String> naoAtualizadas = new ArrayList<>();

        for (Proposta proposta : propostas) {
            CartaoRequest request = new CartaoRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());

            Optional<CartaoResponse> numeroCartao = null;
            try {
                numeroCartao = cartaoClient.cartaoResponse(request);
                Cartao novoCartao = new Cartao(numeroCartao.get().id,proposta);
                //proposta.setCartao(novoCartao);
                manager.persist(novoCartao);
                atualizadas.add(proposta.toStringReduzida());
            } catch (Exception handlerException) {
                naoAtualizadas.add(proposta.toStringReduzida());
                System.out.println("Não foi possível atualizar a proposta: "
                        + proposta.getId() + " - nome: " + proposta.getNome());
            }
        }

        String texto = "Foram atualizadas " + atualizadas.size()
                + " propostas:\n" + atualizadas
                + "\n\nNão foram atualizadas " + naoAtualizadas.size()
                + " propostas:\n" + naoAtualizadas;

        return ResponseEntity.ok().body(texto);
    }
}
