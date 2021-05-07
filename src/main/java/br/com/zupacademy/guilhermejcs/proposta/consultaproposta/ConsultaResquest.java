package br.com.zupacademy.guilhermejcs.proposta.consultaproposta;

public class ConsultaResquest {

    String documento;
    String nome;
    String idProposta;

    public ConsultaResquest() {
    }

    public ConsultaResquest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    @Override
    public String toString() {
        return "ConsultaResquest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }

    public Consulta toModel(){
        return new Consulta(this.documento,this.nome,this.idProposta);
    }


    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
