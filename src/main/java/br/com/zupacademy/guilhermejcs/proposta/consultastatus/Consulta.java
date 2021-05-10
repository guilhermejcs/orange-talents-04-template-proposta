package br.com.zupacademy.guilhermejcs.proposta.consultastatus;

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

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", resultadoSolicitacao='" + resultadoSolicitacao + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}
