package br.com.zupacademy.guilhermejcs.proposta.avisodeviagem;

import br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao.CartaoBloqueioRequest;
import br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao.CartaoBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "avisoViagem", url = "${CARTAO_CLIENT}")
public interface AvisoViagemClient {

    @PostMapping(value = "/api/cartoes/{id}/avisos")
    NovoAvisoViagemResponse statusAviso(@PathVariable ("id") String id, @RequestBody NovoAvisoViagemRequest request);

}