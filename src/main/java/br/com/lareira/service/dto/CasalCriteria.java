package br.com.lareira.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link br.com.lareira.domain.Casal} entity. This class is used
 * in {@link br.com.lareira.web.rest.CasalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /casals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CasalCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter maridoNome;

    private StringFilter maridoSobrenome;

    private LocalDateFilter maridoDataNascimento;

    private StringFilter maridoProfissao;

    private StringFilter maridoTelCelular;

    private StringFilter maridoEmail;

    private StringFilter maridoProblemaSaude;

    private StringFilter esposaNome;

    private StringFilter esposaSobrenome;

    private LocalDateFilter esposaDataNascimento;

    private StringFilter esposaProfissao;

    private StringFilter esposaTelCelular;

    private StringFilter esposaEmail;

    private StringFilter esposaProblemaSaude;

    private LongFilter idId;

    private LongFilter idLareiraId;

    public CasalCriteria() {
    }

    public CasalCriteria(CasalCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.maridoNome = other.maridoNome == null ? null : other.maridoNome.copy();
        this.maridoSobrenome = other.maridoSobrenome == null ? null : other.maridoSobrenome.copy();
        this.maridoDataNascimento = other.maridoDataNascimento == null ? null : other.maridoDataNascimento.copy();
        this.maridoProfissao = other.maridoProfissao == null ? null : other.maridoProfissao.copy();
        this.maridoTelCelular = other.maridoTelCelular == null ? null : other.maridoTelCelular.copy();
        this.maridoEmail = other.maridoEmail == null ? null : other.maridoEmail.copy();
        this.maridoProblemaSaude = other.maridoProblemaSaude == null ? null : other.maridoProblemaSaude.copy();
        this.esposaNome = other.esposaNome == null ? null : other.esposaNome.copy();
        this.esposaSobrenome = other.esposaSobrenome == null ? null : other.esposaSobrenome.copy();
        this.esposaDataNascimento = other.esposaDataNascimento == null ? null : other.esposaDataNascimento.copy();
        this.esposaProfissao = other.esposaProfissao == null ? null : other.esposaProfissao.copy();
        this.esposaTelCelular = other.esposaTelCelular == null ? null : other.esposaTelCelular.copy();
        this.esposaEmail = other.esposaEmail == null ? null : other.esposaEmail.copy();
        this.esposaProblemaSaude = other.esposaProblemaSaude == null ? null : other.esposaProblemaSaude.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
        this.idLareiraId = other.idLareiraId == null ? null : other.idLareiraId.copy();
    }

    @Override
    public CasalCriteria copy() {
        return new CasalCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getMaridoNome() {
        return maridoNome;
    }

    public void setMaridoNome(StringFilter maridoNome) {
        this.maridoNome = maridoNome;
    }

    public StringFilter getMaridoSobrenome() {
        return maridoSobrenome;
    }

    public void setMaridoSobrenome(StringFilter maridoSobrenome) {
        this.maridoSobrenome = maridoSobrenome;
    }

    public LocalDateFilter getMaridoDataNascimento() {
        return maridoDataNascimento;
    }

    public void setMaridoDataNascimento(LocalDateFilter maridoDataNascimento) {
        this.maridoDataNascimento = maridoDataNascimento;
    }

    public StringFilter getMaridoProfissao() {
        return maridoProfissao;
    }

    public void setMaridoProfissao(StringFilter maridoProfissao) {
        this.maridoProfissao = maridoProfissao;
    }

    public StringFilter getMaridoTelCelular() {
        return maridoTelCelular;
    }

    public void setMaridoTelCelular(StringFilter maridoTelCelular) {
        this.maridoTelCelular = maridoTelCelular;
    }

    public StringFilter getMaridoEmail() {
        return maridoEmail;
    }

    public void setMaridoEmail(StringFilter maridoEmail) {
        this.maridoEmail = maridoEmail;
    }

    public StringFilter getMaridoProblemaSaude() {
        return maridoProblemaSaude;
    }

    public void setMaridoProblemaSaude(StringFilter maridoProblemaSaude) {
        this.maridoProblemaSaude = maridoProblemaSaude;
    }

    public StringFilter getEsposaNome() {
        return esposaNome;
    }

    public void setEsposaNome(StringFilter esposaNome) {
        this.esposaNome = esposaNome;
    }

    public StringFilter getEsposaSobrenome() {
        return esposaSobrenome;
    }

    public void setEsposaSobrenome(StringFilter esposaSobrenome) {
        this.esposaSobrenome = esposaSobrenome;
    }

    public LocalDateFilter getEsposaDataNascimento() {
        return esposaDataNascimento;
    }

    public void setEsposaDataNascimento(LocalDateFilter esposaDataNascimento) {
        this.esposaDataNascimento = esposaDataNascimento;
    }

    public StringFilter getEsposaProfissao() {
        return esposaProfissao;
    }

    public void setEsposaProfissao(StringFilter esposaProfissao) {
        this.esposaProfissao = esposaProfissao;
    }

    public StringFilter getEsposaTelCelular() {
        return esposaTelCelular;
    }

    public void setEsposaTelCelular(StringFilter esposaTelCelular) {
        this.esposaTelCelular = esposaTelCelular;
    }

    public StringFilter getEsposaEmail() {
        return esposaEmail;
    }

    public void setEsposaEmail(StringFilter esposaEmail) {
        this.esposaEmail = esposaEmail;
    }

    public StringFilter getEsposaProblemaSaude() {
        return esposaProblemaSaude;
    }

    public void setEsposaProblemaSaude(StringFilter esposaProblemaSaude) {
        this.esposaProblemaSaude = esposaProblemaSaude;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }

    public LongFilter getIdLareiraId() {
        return idLareiraId;
    }

    public void setIdLareiraId(LongFilter idLareiraId) {
        this.idLareiraId = idLareiraId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CasalCriteria that = (CasalCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(maridoNome, that.maridoNome) &&
            Objects.equals(maridoSobrenome, that.maridoSobrenome) &&
            Objects.equals(maridoDataNascimento, that.maridoDataNascimento) &&
            Objects.equals(maridoProfissao, that.maridoProfissao) &&
            Objects.equals(maridoTelCelular, that.maridoTelCelular) &&
            Objects.equals(maridoEmail, that.maridoEmail) &&
            Objects.equals(maridoProblemaSaude, that.maridoProblemaSaude) &&
            Objects.equals(esposaNome, that.esposaNome) &&
            Objects.equals(esposaSobrenome, that.esposaSobrenome) &&
            Objects.equals(esposaDataNascimento, that.esposaDataNascimento) &&
            Objects.equals(esposaProfissao, that.esposaProfissao) &&
            Objects.equals(esposaTelCelular, that.esposaTelCelular) &&
            Objects.equals(esposaEmail, that.esposaEmail) &&
            Objects.equals(esposaProblemaSaude, that.esposaProblemaSaude) &&
            Objects.equals(idId, that.idId) &&
            Objects.equals(idLareiraId, that.idLareiraId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        maridoNome,
        maridoSobrenome,
        maridoDataNascimento,
        maridoProfissao,
        maridoTelCelular,
        maridoEmail,
        maridoProblemaSaude,
        esposaNome,
        esposaSobrenome,
        esposaDataNascimento,
        esposaProfissao,
        esposaTelCelular,
        esposaEmail,
        esposaProblemaSaude,
        idId,
        idLareiraId
        );
    }

    @Override
    public String toString() {
        return "CasalCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (maridoNome != null ? "maridoNome=" + maridoNome + ", " : "") +
                (maridoSobrenome != null ? "maridoSobrenome=" + maridoSobrenome + ", " : "") +
                (maridoDataNascimento != null ? "maridoDataNascimento=" + maridoDataNascimento + ", " : "") +
                (maridoProfissao != null ? "maridoProfissao=" + maridoProfissao + ", " : "") +
                (maridoTelCelular != null ? "maridoTelCelular=" + maridoTelCelular + ", " : "") +
                (maridoEmail != null ? "maridoEmail=" + maridoEmail + ", " : "") +
                (maridoProblemaSaude != null ? "maridoProblemaSaude=" + maridoProblemaSaude + ", " : "") +
                (esposaNome != null ? "esposaNome=" + esposaNome + ", " : "") +
                (esposaSobrenome != null ? "esposaSobrenome=" + esposaSobrenome + ", " : "") +
                (esposaDataNascimento != null ? "esposaDataNascimento=" + esposaDataNascimento + ", " : "") +
                (esposaProfissao != null ? "esposaProfissao=" + esposaProfissao + ", " : "") +
                (esposaTelCelular != null ? "esposaTelCelular=" + esposaTelCelular + ", " : "") +
                (esposaEmail != null ? "esposaEmail=" + esposaEmail + ", " : "") +
                (esposaProblemaSaude != null ? "esposaProblemaSaude=" + esposaProblemaSaude + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
                (idLareiraId != null ? "idLareiraId=" + idLareiraId + ", " : "") +
            "}";
    }

}
