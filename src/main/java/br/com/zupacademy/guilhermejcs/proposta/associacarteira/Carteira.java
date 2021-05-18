package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private @NotNull Cartao cartao;
    @Enumerated(EnumType.STRING)
    private CarteiraDigital carteiraDigital;
    private String idCarteira;

    @Deprecated
    public Carteira() {
    }

    public Carteira(Cartao cartao, CarteiraDigital carteiraDigital, String idCarteira) {
        this.cartao = cartao;
        this.carteiraDigital = carteiraDigital;
        this.idCarteira = idCarteira;
    }

    public String getIdCarteira() {
        return idCarteira;
    }

    public String getCarteiraDigital() {
        return carteiraDigital.toString();
    }
}