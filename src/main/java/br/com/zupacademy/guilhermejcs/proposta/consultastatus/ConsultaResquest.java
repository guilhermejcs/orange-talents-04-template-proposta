package br.com.zupacademy.guilhermejcs.proposta.consultastatus;

import br.com.zupacademy.guilhermejcs.proposta.novaproposta.CpfCnpj;

import javax.validation.constraints.NotBlank;

public class ConsultaResquest {

    @NotBlank
    @CpfCnpj
    String documento;
    @NotBlank
    String nome;
    @NotBlank
    String idProposta;

    public ConsultaResquest() {
    }

    public ConsultaResquest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public Consulta toModel() {
        return new Consulta(this.documento, this.nome, this.idProposta);
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
