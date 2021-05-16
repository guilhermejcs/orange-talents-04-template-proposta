package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cartaoBloqueio", url = "${CARTAO_CLIENT}")
public interface CartaoBloqueioClient {

    @PostMapping(value = "/api/cartoes/{id}/bloqueios")
    CartaoBloqueioResponse statusBloqueio(@PathVariable ("id") String id, @RequestBody CartaoBloqueioRequest request);

}