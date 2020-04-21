package br.com.lareira.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.lareira.domain.Casal} entity.
 */
public class CasalDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String maridoNome;

    private String maridoSobrenome;

    private LocalDate maridoDataNascimento;

    private String maridoProfissao;

    private String maridoTelCelular;

    private String maridoEmail;

    private String maridoProblemaSaude;

    @NotNull
    @Size(min = 3, max = 100)
    private String esposaNome;

    private String esposaSobrenome;

    private LocalDate esposaDataNascimento;

    private String esposaProfissao;

    private String esposaTelCelular;

    private String esposaEmail;

    private String esposaProblemaSaude;


    private Long idLareiraId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaridoNome() {
        return maridoNome;
    }

    public void setMaridoNome(String maridoNome) {
        this.maridoNome = maridoNome;
    }

    public String getMaridoSobrenome() {
        return maridoSobrenome;
    }

    public void setMaridoSobrenome(String maridoSobrenome) {
        this.maridoSobrenome = maridoSobrenome;
    }

    public LocalDate getMaridoDataNascimento() {
        return maridoDataNascimento;
    }

    public void setMaridoDataNascimento(LocalDate maridoDataNascimento) {
        this.maridoDataNascimento = maridoDataNascimento;
    }

    public String getMaridoProfissao() {
        return maridoProfissao;
    }

    public void setMaridoProfissao(String maridoProfissao) {
        this.maridoProfissao = maridoProfissao;
    }

    public String getMaridoTelCelular() {
        return maridoTelCelular;
    }

    public void setMaridoTelCelular(String maridoTelCelular) {
        this.maridoTelCelular = maridoTelCelular;
    }

    public String getMaridoEmail() {
        return maridoEmail;
    }

    public void setMaridoEmail(String maridoEmail) {
        this.maridoEmail = maridoEmail;
    }

    public String getMaridoProblemaSaude() {
        return maridoProblemaSaude;
    }

    public void setMaridoProblemaSaude(String maridoProblemaSaude) {
        this.maridoProblemaSaude = maridoProblemaSaude;
    }

    public String getEsposaNome() {
        return esposaNome;
    }

    public void setEsposaNome(String esposaNome) {
        this.esposaNome = esposaNome;
    }

    public String getEsposaSobrenome() {
        return esposaSobrenome;
    }

    public void setEsposaSobrenome(String esposaSobrenome) {
        this.esposaSobrenome = esposaSobrenome;
    }

    public LocalDate getEsposaDataNascimento() {
        return esposaDataNascimento;
    }

    public void setEsposaDataNascimento(LocalDate esposaDataNascimento) {
        this.esposaDataNascimento = esposaDataNascimento;
    }

    public String getEsposaProfissao() {
        return esposaProfissao;
    }

    public void setEsposaProfissao(String esposaProfissao) {
        this.esposaProfissao = esposaProfissao;
    }

    public String getEsposaTelCelular() {
        return esposaTelCelular;
    }

    public void setEsposaTelCelular(String esposaTelCelular) {
        this.esposaTelCelular = esposaTelCelular;
    }

    public String getEsposaEmail() {
        return esposaEmail;
    }

    public void setEsposaEmail(String esposaEmail) {
        this.esposaEmail = esposaEmail;
    }

    public String getEsposaProblemaSaude() {
        return esposaProblemaSaude;
    }

    public void setEsposaProblemaSaude(String esposaProblemaSaude) {
        this.esposaProblemaSaude = esposaProblemaSaude;
    }

    public Long getIdLareiraId() {
        return idLareiraId;
    }

    public void setIdLareiraId(Long lareiraId) {
        this.idLareiraId = lareiraId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CasalDTO casalDTO = (CasalDTO) o;
        if (casalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CasalDTO{" +
            "id=" + getId() +
            ", maridoNome='" + getMaridoNome() + "'" +
            ", maridoSobrenome='" + getMaridoSobrenome() + "'" +
            ", maridoDataNascimento='" + getMaridoDataNascimento() + "'" +
            ", maridoProfissao='" + getMaridoProfissao() + "'" +
            ", maridoTelCelular='" + getMaridoTelCelular() + "'" +
            ", maridoEmail='" + getMaridoEmail() + "'" +
            ", maridoProblemaSaude='" + getMaridoProblemaSaude() + "'" +
            ", esposaNome='" + getEsposaNome() + "'" +
            ", esposaSobrenome='" + getEsposaSobrenome() + "'" +
            ", esposaDataNascimento='" + getEsposaDataNascimento() + "'" +
            ", esposaProfissao='" + getEsposaProfissao() + "'" +
            ", esposaTelCelular='" + getEsposaTelCelular() + "'" +
            ", esposaEmail='" + getEsposaEmail() + "'" +
            ", esposaProblemaSaude='" + getEsposaProblemaSaude() + "'" +
            ", idLareiraId=" + getIdLareiraId() +
            "}";
    }
}
