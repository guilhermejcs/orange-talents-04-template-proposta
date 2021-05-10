package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NovaPropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(@CpfCnpj String documento);

    List<Proposta> findByIdCartao(String idCartao);
}
