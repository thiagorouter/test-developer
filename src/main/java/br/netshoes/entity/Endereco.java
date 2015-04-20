package br.netshoes.entity;

import javax.persistence.*;

/**
 * Created by Thiago on 15/04/2015.
 */
@Entity
public class Endereco implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String unidadeFederativa;

    @Column
    private String cidade;

    @Column
    private String bairro;

    @Column
    private String numero;
    @Column
    private String complemento;

    @Column
    private String cep;

    public Endereco(final String unidadeFederativa, final String cidade, final String bairro, final String numero, final String complemento, final String cep) {
        this.unidadeFederativa = unidadeFederativa;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
    }

    public Endereco() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (!unidadeFederativa.equals(endereco.unidadeFederativa)) return false;
        if (!cidade.equals(endereco.cidade)) return false;
        if (!bairro.equals(endereco.bairro)) return false;
        if (numero != null ? !numero.equals(endereco.numero) : endereco.numero != null) return false;
        return cep.equals(endereco.cep);

    }

    @Override
    public int hashCode() {
        int result = unidadeFederativa.hashCode();
        result = 31 * result + cidade.hashCode();
        result = 31 * result + bairro.hashCode();
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + cep.hashCode();
        return result;
    }

    public String getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }
}
