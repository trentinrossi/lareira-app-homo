package br.com.lareira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Casal.
 */
@Entity
@Table(name = "casal")
public class Casal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marido_nome")
    private String maridoNome;

    @Column(name = "marido_sobrenome")
    private String maridoSobrenome;

    @Column(name = "marido_data_nascimento")
    private LocalDate maridoDataNascimento;

    @Column(name = "marido_profissao")
    private String maridoProfissao;

    @Column(name = "marido_tel_celular")
    private String maridoTelCelular;

    @Column(name = "marido_email")
    private String maridoEmail;

    @Column(name = "marido_problema_saude")
    private String maridoProblemaSaude;

    @Column(name = "esposa_nome")
    private String esposaNome;

    @Column(name = "esposa_sobrenome")
    private String esposaSobrenome;

    @Column(name = "esposa_data_nascimento")
    private LocalDate esposaDataNascimento;

    @Column(name = "esposa_profissao")
    private String esposaProfissao;

    @Column(name = "esposa_tel_celular")
    private String esposaTelCelular;

    @Column(name = "esposa_email")
    private String esposaEmail;

    @Column(name = "esposa_problema_saude")
    private String esposaProblemaSaude;

    @ManyToOne
    @JsonIgnoreProperties("casals")
    private Lareira lareira;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaridoNome() {
        return maridoNome;
    }

    public Casal maridoNome(String maridoNome) {
        this.maridoNome = maridoNome;
        return this;
    }

    public void setMaridoNome(String maridoNome) {
        this.maridoNome = maridoNome;
    }

    public String getMaridoSobrenome() {
        return maridoSobrenome;
    }

    public Casal maridoSobrenome(String maridoSobrenome) {
        this.maridoSobrenome = maridoSobrenome;
        return this;
    }

    public void setMaridoSobrenome(String maridoSobrenome) {
        this.maridoSobrenome = maridoSobrenome;
    }

    public LocalDate getMaridoDataNascimento() {
        return maridoDataNascimento;
    }

    public Casal maridoDataNascimento(LocalDate maridoDataNascimento) {
        this.maridoDataNascimento = maridoDataNascimento;
        return this;
    }

    public void setMaridoDataNascimento(LocalDate maridoDataNascimento) {
        this.maridoDataNascimento = maridoDataNascimento;
    }

    public String getMaridoProfissao() {
        return maridoProfissao;
    }

    public Casal maridoProfissao(String maridoProfissao) {
        this.maridoProfissao = maridoProfissao;
        return this;
    }

    public void setMaridoProfissao(String maridoProfissao) {
        this.maridoProfissao = maridoProfissao;
    }

    public String getMaridoTelCelular() {
        return maridoTelCelular;
    }

    public Casal maridoTelCelular(String maridoTelCelular) {
        this.maridoTelCelular = maridoTelCelular;
        return this;
    }

    public void setMaridoTelCelular(String maridoTelCelular) {
        this.maridoTelCelular = maridoTelCelular;
    }

    public String getMaridoEmail() {
        return maridoEmail;
    }

    public Casal maridoEmail(String maridoEmail) {
        this.maridoEmail = maridoEmail;
        return this;
    }

    public void setMaridoEmail(String maridoEmail) {
        this.maridoEmail = maridoEmail;
    }

    public String getMaridoProblemaSaude() {
        return maridoProblemaSaude;
    }

    public Casal maridoProblemaSaude(String maridoProblemaSaude) {
        this.maridoProblemaSaude = maridoProblemaSaude;
        return this;
    }

    public void setMaridoProblemaSaude(String maridoProblemaSaude) {
        this.maridoProblemaSaude = maridoProblemaSaude;
    }

    public String getEsposaNome() {
        return esposaNome;
    }

    public Casal esposaNome(String esposaNome) {
        this.esposaNome = esposaNome;
        return this;
    }

    public void setEsposaNome(String esposaNome) {
        this.esposaNome = esposaNome;
    }

    public String getEsposaSobrenome() {
        return esposaSobrenome;
    }

    public Casal esposaSobrenome(String esposaSobrenome) {
        this.esposaSobrenome = esposaSobrenome;
        return this;
    }

    public void setEsposaSobrenome(String esposaSobrenome) {
        this.esposaSobrenome = esposaSobrenome;
    }

    public LocalDate getEsposaDataNascimento() {
        return esposaDataNascimento;
    }

    public Casal esposaDataNascimento(LocalDate esposaDataNascimento) {
        this.esposaDataNascimento = esposaDataNascimento;
        return this;
    }

    public void setEsposaDataNascimento(LocalDate esposaDataNascimento) {
        this.esposaDataNascimento = esposaDataNascimento;
    }

    public String getEsposaProfissao() {
        return esposaProfissao;
    }

    public Casal esposaProfissao(String esposaProfissao) {
        this.esposaProfissao = esposaProfissao;
        return this;
    }

    public void setEsposaProfissao(String esposaProfissao) {
        this.esposaProfissao = esposaProfissao;
    }

    public String getEsposaTelCelular() {
        return esposaTelCelular;
    }

    public Casal esposaTelCelular(String esposaTelCelular) {
        this.esposaTelCelular = esposaTelCelular;
        return this;
    }

    public void setEsposaTelCelular(String esposaTelCelular) {
        this.esposaTelCelular = esposaTelCelular;
    }

    public String getEsposaEmail() {
        return esposaEmail;
    }

    public Casal esposaEmail(String esposaEmail) {
        this.esposaEmail = esposaEmail;
        return this;
    }

    public void setEsposaEmail(String esposaEmail) {
        this.esposaEmail = esposaEmail;
    }

    public String getEsposaProblemaSaude() {
        return esposaProblemaSaude;
    }

    public Casal esposaProblemaSaude(String esposaProblemaSaude) {
        this.esposaProblemaSaude = esposaProblemaSaude;
        return this;
    }

    public void setEsposaProblemaSaude(String esposaProblemaSaude) {
        this.esposaProblemaSaude = esposaProblemaSaude;
    }

    public Lareira getLareira() {
        return lareira;
    }

    public Casal lareira(Lareira lareira) {
        this.lareira = lareira;
        return this;
    }

    public void setLareira(Lareira lareira) {
        this.lareira = lareira;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Casal)) {
            return false;
        }
        return id != null && id.equals(((Casal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Casal{" +
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
            "}";
    }
}
