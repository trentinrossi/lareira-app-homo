package br.com.lareira.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Lareira.
 */
@Entity
@Table(name = "lareira")
public class Lareira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "lareira")
    private Set<Casal> casals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Lareira nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Lareira endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public Lareira bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public Lareira cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public Lareira cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Lareira estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public Lareira telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Casal> getCasals() {
        return casals;
    }

    public Lareira casals(Set<Casal> casals) {
        this.casals = casals;
        return this;
    }

    public Lareira addCasal(Casal casal) {
        this.casals.add(casal);
        casal.setLareira(this);
        return this;
    }

    public Lareira removeCasal(Casal casal) {
        this.casals.remove(casal);
        casal.setLareira(null);
        return this;
    }

    public void setCasals(Set<Casal> casals) {
        this.casals = casals;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lareira)) {
            return false;
        }
        return id != null && id.equals(((Lareira) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lareira{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", bairro='" + getBairro() + "'" +
            ", cep='" + getCep() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }
}
