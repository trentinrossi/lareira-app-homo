package br.com.lareira.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import br.com.lareira.domain.enumeration.Sexo;

/**
 * A DTO for the {@link br.com.lareira.domain.Filho} entity.
 */
public class FilhoDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    private Sexo sexo;

    private LocalDate dataNascimento;


    private Long casalId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCasalId() {
        return casalId;
    }

    public void setCasalId(Long casalId) {
        this.casalId = casalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilhoDTO filhoDTO = (FilhoDTO) o;
        if (filhoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filhoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilhoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sexo='" + getSexo() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", casalId=" + getCasalId() +
            "}";
    }
}
