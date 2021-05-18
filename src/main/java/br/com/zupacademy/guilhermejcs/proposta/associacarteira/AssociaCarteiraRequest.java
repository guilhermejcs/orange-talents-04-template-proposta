package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociaCarteiraRequest {
    @Email
    String email;
    @NotBlank
    String carteira;

    @Deprecated
    public AssociaCarteiraRequest() {
    }

    public AssociaCarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
