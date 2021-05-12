package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import br.com.zupacademy.guilhermejcs.proposta.criabiometria.Biometria;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    @OneToOne
    private Proposta proposta;
    @ElementCollection
    private Set<Biometria> biometrias = new HashSet<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numeroCartao, Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void adicionaBiometria(String digital) {
        this.biometrias.add(new Biometria(digital));
    }
}
