package br.com.zupacademy.guilhermejcs.proposta.consultaproposta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.nio.channels.ReadPendingException;

@RestController
@RequestMapping(value = "/consultas")
public class consultaController {


    @PostMapping
    public ResponseEntity<?>
    consulta(@RequestBody @Valid ConsultaResquest request){


        return ResponseEntity.ok().body(request.toString());
    }


}
