package br.com.zupacademy.guilhermejcs.proposta.associacarteira;

public class AssociaCarteiraResponse {
    String resultado;
    String id;

    @Deprecated
    public AssociaCarteiraResponse() {
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
