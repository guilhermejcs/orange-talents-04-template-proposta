package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

import br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao.PossiveisStatusUso;
import br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao.StatusUso;
import br.com.zupacademy.guilhermejcs.proposta.criabiometria.Biometria;
import br.com.zupacademy.guilhermejcs.proposta.novaproposta.Proposta;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @OneToMany(mappedBy = "cartao")
    private List<StatusUso> statusUsos = new ArrayList<>();


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

    public void bloqueia(@NotBlank String userAgent,
                         @NotBlank String ipRemoto) {
        Assert.state(!this.biometrias.isEmpty(),
                "Nenhum cartão pode ser bloqueado se não tiver digital associada");
        this.statusUsos.add(new StatusUso(PossiveisStatusUso.BLOQUEADO, this,
                userAgent, ipRemoto));
    }
}
