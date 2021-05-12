package br.com.zupacademy.guilhermejcs.proposta.criabiometria;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CriaBiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping(value = "/cartao/{id}/biometria")
    @Transactional
    public ResponseEntity<?> cria(@PathVariable("id") Long id,
                                  @RequestBody @Valid NovaBiometriaRequest request, UriComponentsBuilder builder) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (request.getDigital().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        cartao.get().adicionaBiometria(request.getDigital());
        URI enderecoBiometria = builder.path("/cartao/{id}/biometria").build(cartao.get().getId());

        return ResponseEntity.created(enderecoBiometria).build();
    }
}