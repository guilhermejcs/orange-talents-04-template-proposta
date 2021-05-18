package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "carteiraDigital", url = "${CARTAO_CLIENT}")
public interface AssociaCarteiraClient {
    @PostMapping(value = "/api/cartoes/{id}/carteiras")
    AssociaCarteiraResponse carteira(@PathVariable("id") String id, @RequestBody AssociaCarteiraRequest request);
}
