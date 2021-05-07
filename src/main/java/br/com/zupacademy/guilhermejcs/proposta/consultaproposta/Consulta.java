package br.com.zupacademy.guilhermejcs.proposta.consultaproposta;

public class Consulta {


    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;

    public Consulta() {
    }

    public Consulta(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public void setResultadoSolicitacao(String resultadoSolicitacao) {
        this.resultadoSolicitacao = resultadoSolicitacao;
    }
}
