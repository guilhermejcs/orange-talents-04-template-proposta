package br.com.zupacademy.guilhermejcs.proposta.bloqueiacartao;

public class CartaoBloqueioRequest {
    String sistemaResponsavel;

    public CartaoBloqueioRequest() {
    }

    public CartaoBloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}
