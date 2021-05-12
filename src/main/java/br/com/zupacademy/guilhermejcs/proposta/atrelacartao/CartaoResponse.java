package br.com.zupacademy.guilhermejcs.proposta.atrelacartao;

public class CartaoResponse {
    String documento;
    String nome;
    Long idProposta;
    String id;

    public CartaoResponse(String documento, String nome, Long idProposta, String id) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
