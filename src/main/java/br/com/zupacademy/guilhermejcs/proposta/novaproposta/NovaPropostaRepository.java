package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findById(Long id);

    Optional<Proposta> findByDocumento(byte[] documento);

    List<Proposta> findByCartaoAndStatus(Cartao cartao, Avaliacao Status);

    Optional<Proposta> findByCartao(Cartao cartao);
}
