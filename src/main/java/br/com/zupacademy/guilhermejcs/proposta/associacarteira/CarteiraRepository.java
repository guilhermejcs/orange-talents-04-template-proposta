package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

import br.com.zupacademy.guilhermejcs.proposta.atrelacartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    boolean existsByCartaoAndCarteiraDigital(Cartao cartao, CarteiraDigital carteiraDigital);
}
