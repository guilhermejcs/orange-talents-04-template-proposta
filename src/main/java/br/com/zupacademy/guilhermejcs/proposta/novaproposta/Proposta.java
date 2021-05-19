package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import br.com.zupacademy.guilhermejcs.proposta.criptografadados.EncriptaDecriptaAES;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private byte[] documento;
    @Enumerated(EnumType.STRING)
    private Avaliacao status;
    @OneToOne(mappedBy = "proposta",cascade = CascadeType.MERGE)
    private Cartao cartao;

    public Proposta() {
    }

    public Proposta(@Email @NotBlank String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @Positive BigDecimal salario,
                    @NotNull byte[] documento) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.documento = documento;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Avaliacao getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDoc() throws Exception {
        return EncriptaDecriptaAES.decrypt(documento);
    }

    public byte[] getDocumento() {
        return documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setStatus(Avaliacao status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String toStringReduzida() {
        return "Proposta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}
