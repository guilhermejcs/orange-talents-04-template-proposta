package br.com.zupacademy.guilhermejcs.proposta.consultastatus;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "consulta", url = "http://localhost:9999")
public interface ConsultaClient {

    @PostMapping(value = "/api/solicitacao")
    Consulta consulta(@RequestBody Consulta request);
}
