package br.netshoes.rest.resources.response;

import br.netshoes.entity.Logradouro;

import java.io.Serializable;

public class LogradouroResourceResponse extends BaseResponse implements Serializable {

    private String rua;

    private String bairro;

    private String cidade;

    private String unidadeFederativa;

    public LogradouroResourceResponse(final Logradouro logradouro) {

        this.rua = logradouro.getNome();
        this.bairro = logradouro.getBairro();
        this.cidade = logradouro.getCidade();
        this.unidadeFederativa = logradouro.getUnidadeFederativa();

    }

    public LogradouroResourceResponse(final String mensagemDeErro) {
        adicionarMensagemDeErro(mensagemDeErro);
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUnidadeFederativa() {
        return unidadeFederativa;
    }

}
