package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import br.com.zupacademy.guilhermejcs.proposta.compartilhado.ExecutorTransacao;
import br.com.zupacademy.guilhermejcs.proposta.criabiometria.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class BloqueiaCartaoController {

    @Autowired
    CartaoBloqueioClient cartaoBloqueioClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueiaCartaoRepository bloqueiaCartaoRepository;

    @Autowired
    private ExecutorTransacao transacao;

    @PostMapping(value = "/cartoes/{id}/bloqueia")
    public ResponseEntity<?> bloqueia(@PathVariable("id") Long id,
                                      @RequestHeader HttpHeaders headers,
                                      HttpServletRequest httpRequest) {

        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);
        String ipRemoto = httpRequest.getRemoteAddr();
        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        Cartao cartao;
        Boolean bloqueado = null;

        if (possivelCartao.isPresent()) {
            cartao = possivelCartao.get();
            bloqueado = bloqueiaCartaoRepository.existsByCartao(cartao);
        } else {
            return ResponseEntity.notFound().build();
        }

        if (bloqueado) {
            return ResponseEntity.unprocessableEntity().body("Cartão bloqueado");
        }

        if (id == null) {
            return ResponseEntity.badRequest().body("Id não informado");
        }

        if (headers.isEmpty()) {
            return ResponseEntity.badRequest().body("Header não informado");
        }

        if (ipRemoto.isEmpty()) {
            return ResponseEntity.badRequest().body("IP Remoto não informado");
        }

        CartaoBloqueioResponse bloqStatus = null;

        CartaoBloqueioRequest requestBloq = new CartaoBloqueioRequest("string");
        String numeroCartao = cartao.getNumeroCartao();
        try {
            bloqStatus = cartaoBloqueioClient.statusBloqueio(numeroCartao,requestBloq);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Falha no sistema remoto");
        }

        if(!bloqStatus.equals("BLOQUEADO")){
            return ResponseEntity.unprocessableEntity().body("Falha no sistema remoto");
        }

        StatusUsoRequest request = new StatusUsoRequest(PossiveisStatusUso.BLOQUEADO, cartao, userAgent, ipRemoto);
        StatusUso cartaoBloqueio = request.toModel();
        
        transacao.salvaEComita(cartaoBloqueio);

        return ResponseEntity.ok().body("Cartão com id " + id + " bloqueado com sucesso");
    }
}
