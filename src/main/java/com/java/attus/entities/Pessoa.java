package com.java.attus.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Pessoa implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa")
    private Set<Endereco> enderecos = new HashSet<>();

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setPessoa(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(nomeCompleto, pessoa.nomeCompleto) && Objects.equals(dataNascimento, pessoa.dataNascimento) && Objects.equals(enderecos, pessoa.enderecos);
    }

}