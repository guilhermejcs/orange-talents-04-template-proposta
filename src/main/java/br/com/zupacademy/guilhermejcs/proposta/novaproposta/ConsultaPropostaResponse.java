package br.com.zupacademy.guilhermejcs.proposta.novaproposta;

public class ConsultaPropostaResponse {

    private String id;
    private String nome;
    private String documento;
    private String status;
    private String cartao;

    public ConsultaPropostaResponse(Proposta proposta) {
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

    @Override
    public String toString() {
        return "ConsultaPropostaResponse{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", status='" + status + '\'' +
                ", cartao='" + cartao + '\'' +
                '}';
    }
}
