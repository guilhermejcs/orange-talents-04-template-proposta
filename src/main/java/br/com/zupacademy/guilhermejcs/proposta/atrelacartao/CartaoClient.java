package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(name = "cartao", url = "${CARTAO_CLIENT}")
public interface CartaoClient {

    @PostMapping(value = "/api/cartoes")
    Optional<Cartao> cartao(@RequestBody CartaoRequest request);

}
