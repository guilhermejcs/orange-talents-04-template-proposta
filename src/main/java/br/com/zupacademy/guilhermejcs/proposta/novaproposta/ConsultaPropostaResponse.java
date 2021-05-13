package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

public class ConsultaPropostaResponse {

    private String id;
    private String nome;
    private String documento;
    private String status;
    private String cartao;

    public void atribuirDados(Proposta proposta) {
        this.id = proposta.getId().toString();
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        if (proposta.getStatus()==null) {
            this.status = "Não avaliada";
        } else {
            this.status = proposta.getStatus().toString();
        }
        if (proposta.getCartao() == null) {
            this.cartao = "Sem cartão associado";
        } else {
            this.cartao = proposta.getCartao().getNumeroCartao();
        }
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getStatus() {
        return status;
    }

    public String getCartao() {
        return cartao;
    }
}
