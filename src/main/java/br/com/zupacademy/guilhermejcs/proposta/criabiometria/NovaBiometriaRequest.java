package br.com.zupacademy.guilhermejcs.proposta.criabiometria;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    private String digital;

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public String getDigital() {
        return digital;
    }
}
