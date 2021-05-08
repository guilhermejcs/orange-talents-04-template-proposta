package br.com.zupacademy.guilhermejcs.proposta.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @Autowired
    HealthClient healthClient;

    @GetMapping(value ="/status_sistema")
    public ResponseEntity<?> healthCheck(){
              Health status =   healthClient.health();
              if(status.status.equals("UP")){
                  return ResponseEntity.ok().body("API Ativa");
              } else {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("API fora de operação");
              }
    }
}
