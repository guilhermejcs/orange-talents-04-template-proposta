package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import br.com.zupacademy.guilhermejcs.proposta.novaproposta.NovaPropostaRepository;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> atrelaCartao() {
        List<Proposta> propostas = novaPropostaRepository.findByIdCartao(null);
        List<Proposta> atualizadas = new ArrayList<>();
        List<Proposta> naoAtualizadas = new ArrayList<>();

        for (Proposta proposta : propostas) {
            CartaoRequest request = new CartaoRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());

            Optional<Cartao> idCartao = null;
            try {
                idCartao = cartaoClient.cartao(request);
                proposta.setIdCartao(idCartao.get().id);
                manager.persist(proposta);
                atualizadas.add(proposta);
            } catch (Exception handlerException) {
                naoAtualizadas.add(proposta);
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
