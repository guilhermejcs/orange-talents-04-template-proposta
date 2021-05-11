package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

public class ConsultaPropostaDTO {
    private Long id;
    private String nome;
    private String documento;
    private Avaliacao status;
    private String idCartao;

    public ConsultaPropostaDTO(Long id, String nome, String documento, Avaliacao status, String idCartao) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.status = status;
        this.idCartao = idCartao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Avaliacao getStatus() {
        return status;
    }

    public String getIdCartao() {
        return idCartao;
    }
}
