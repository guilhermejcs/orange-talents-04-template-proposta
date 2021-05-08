package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @Email
    @NotBlank String email;
    private @NotBlank String nome;
    private @NotBlank String endereco;
    private @Positive BigDecimal salario;
    @CpfCnpj
    @NotBlank
    private String documento;
    @Enumerated(EnumType.STRING)
    private avaliacao status;

    public Proposta() {
    }

    public Proposta(@Email @NotBlank String email,
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

    public Long getId() {
        return id;
    }
}
