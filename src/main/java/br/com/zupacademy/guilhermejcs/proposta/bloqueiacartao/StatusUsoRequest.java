package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class StatusUsoRequest {
    @Enumerated(EnumType.STRING)
    private @NotNull  PossiveisStatusUso statusSolicitado;
    private @Valid @NotNull Cartao cartao;
    private @NotBlank String userAgent;
    private @NotBlank String ipRemoto;
    private LocalDateTime instante = LocalDateTime.now();

    public StatusUsoRequest(@NotNull PossiveisStatusUso statusSolicitado,
                            @Valid @NotNull Cartao cartao,
                            @NotBlank String userAgent,
                            @NotBlank String ipRemoto) {
        this.statusSolicitado = statusSolicitado;
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipRemoto = ipRemoto;
    }

    public StatusUso toModel(){
        return new StatusUso(statusSolicitado,cartao,userAgent,ipRemoto);
    }

    public PossiveisStatusUso getStatusSolicitado() {
        return statusSolicitado;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpRemoto() {
        return ipRemoto;
    }

    public LocalDateTime getInstante() {
        return instante;
    }
}
