package br.com.zupacademy.guilhermejcs.proposta.criabiometria;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findById(Long id);
}