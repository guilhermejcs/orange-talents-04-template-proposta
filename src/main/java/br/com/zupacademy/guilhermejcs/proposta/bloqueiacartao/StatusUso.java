package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class StatusUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private @NotNull  PossiveisStatusUso statusSolicitado;
    @ManyToOne
    private @Valid @NotNull Cartao cartao;
    private @NotBlank String userAgent;
    private @NotBlank String ipRemoto;
    private LocalDateTime instante = LocalDateTime.now();

    public StatusUso() {
    }

    public StatusUso(@NotNull PossiveisStatusUso statusSolicitado,
                     @Valid @NotNull Cartao cartao,
                     @NotBlank String userAgent,
                     @NotBlank String ipRemoto) {
        this.statusSolicitado = statusSolicitado;
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipRemoto = ipRemoto;
    }
}
