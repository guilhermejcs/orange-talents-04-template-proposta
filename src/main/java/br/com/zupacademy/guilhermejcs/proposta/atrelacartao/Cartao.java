package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;

import javax.persistence.*;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    @OneToOne
    private Proposta proposta;

    public Cartao() {
    }

    public Cartao(String numeroCartao, Proposta proposta) {
        this.numeroCartao = numeroCartao;
        this.proposta = proposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
