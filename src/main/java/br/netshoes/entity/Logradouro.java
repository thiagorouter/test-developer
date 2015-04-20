package br.netshoes.entity;

import javax.persistence.*;

/**
 * Created by Thiago on 15/04/2015.
 */
@Entity
public class Logradouro implements BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 2)
    private String unidadeFederativa;

    @Column(length = 1000)
    private String cidade;

    @Column(length = 1000)
    private String bairro;

    @Column(nullable = false, length = 8, unique = true)
    private String cep;

    @Column(length = 15, nullable = false)
    private String tipoLogradouro;

    public Logradouro() {
    }

    public Logradouro(final String nome, final String unidadeFederativa, final String cidade, final String bairro, final String cep,
                      final String tipoLogradouro) {
        this.nome = nome;
        this.unidadeFederativa = unidadeFederativa;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.tipoLogradouro = tipoLogradouro;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Logradouro that = (Logradouro) o;

        if (!nome.equals(that.nome)) return false;
        if (!unidadeFederativa.equals(that.unidadeFederativa)) return false;
        if (!cidade.equals(that.cidade)) return false;
        if (!bairro.equals(that.bairro)) return false;
        return cep.equals(that.cep);

    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + unidadeFederativa.hashCode();
        result = 31 * result + cidade.hashCode();
        result = 31 * result + bairro.hashCode();
        result = 31 * result + cep.hashCode();
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(String unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }
}
