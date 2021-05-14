package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueiaCartaoRepository extends JpaRepository<StatusUso,Long> {
    boolean existsByCartao(Cartao cartao);
}
