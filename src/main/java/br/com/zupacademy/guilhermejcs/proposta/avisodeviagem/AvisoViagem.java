package br.com.zupacademy.guilhermejcs.proposta.avisodeviagem;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private @Valid
    @NotNull Cartao cartao;
    private @NotBlank String destino;
    private @NotNull @Future LocalDate dataTermino;
    private @NotBlank String navegador;
    private @NotBlank String remoteAddr;
    @NotNull
    @PastOrPresent
    private LocalDateTime instante = LocalDateTime.now();

    public AvisoViagem(@Valid @NotNull Cartao cartao,
                       @NotBlank String destino,
                       @NotBlank @Future LocalDate dataTermino,
                       @NotBlank String navegador,
                       @NotBlank String remoteAddr) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.navegador = navegador;
        this.remoteAddr = remoteAddr;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }


}
