package br.com.lareira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

import br.com.lareira.domain.enumeration.Sexo;

/**
 * A Filho.
 */
@Entity
@Table(name = "filho")
public class Filho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("filhos")
    private Casal casal;

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

    public Filho nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Filho sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Filho dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Casal getCasal() {
        return casal;
    }

    public Filho casal(Casal casal) {
        this.casal = casal;
        return this;
    }

    public void setCasal(Casal casal) {
        this.casal = casal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Filho)) {
            return false;
        }
        return id != null && id.equals(((Filho) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Filho{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            "}";
    }
}
