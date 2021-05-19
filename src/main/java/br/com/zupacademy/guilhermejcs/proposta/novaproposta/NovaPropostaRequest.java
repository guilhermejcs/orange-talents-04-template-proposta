package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import br.com.zupacademy.guilhermejcs.proposta.criptografadados.EncriptaDecriptaAES;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @NotBlank
    @CpfCnpj
    private String documento;

    public NovaPropostaRequest(@Email @NotBlank String email,
                               @NotBlank String nome,
                               @NotBlank String endereco,
                               @Positive BigDecimal salario,
                               @CpfCnpj String documento) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public Proposta toModel() throws Exception {
        return new Proposta(email, nome, endereco, salario, EncriptaDecriptaAES.encrypt(documento));
    }

    public String getDocumento() {
        return documento;
    }
}