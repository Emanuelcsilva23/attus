package com.java.attus.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Endereco implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "logradouro", nullable = false, length = 100)
    private String logradouro;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 100)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    //indicar que é o endereco principal
    private Boolean principal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(cep, endereco.cep) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado) && Objects.equals(pessoa, endereco.pessoa) && Objects.equals(principal, endereco.principal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logradouro, cep, numero, cidade, estado, pessoa, principal);
    }
}
