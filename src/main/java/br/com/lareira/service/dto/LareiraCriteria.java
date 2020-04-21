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

/**
 * Criteria class for the {@link br.com.lareira.domain.Lareira} entity. This class is used
 * in {@link br.com.lareira.web.rest.LareiraResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /lareiras?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LareiraCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter endereco;

    private StringFilter bairro;

    private StringFilter cep;

    private StringFilter cidade;

    private StringFilter estado;

    private StringFilter telefone;

    private LongFilter idId;

    public LareiraCriteria() {
    }

    public LareiraCriteria(LareiraCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nome = other.nome == null ? null : other.nome.copy();
        this.endereco = other.endereco == null ? null : other.endereco.copy();
        this.bairro = other.bairro == null ? null : other.bairro.copy();
        this.cep = other.cep == null ? null : other.cep.copy();
        this.cidade = other.cidade == null ? null : other.cidade.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.telefone = other.telefone == null ? null : other.telefone.copy();
        this.idId = other.idId == null ? null : other.idId.copy();
    }

    @Override
    public LareiraCriteria copy() {
        return new LareiraCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getEndereco() {
        return endereco;
    }

    public void setEndereco(StringFilter endereco) {
        this.endereco = endereco;
    }

    public StringFilter getBairro() {
        return bairro;
    }

    public void setBairro(StringFilter bairro) {
        this.bairro = bairro;
    }

    public StringFilter getCep() {
        return cep;
    }

    public void setCep(StringFilter cep) {
        this.cep = cep;
    }

    public StringFilter getCidade() {
        return cidade;
    }

    public void setCidade(StringFilter cidade) {
        this.cidade = cidade;
    }

    public StringFilter getEstado() {
        return estado;
    }

    public void setEstado(StringFilter estado) {
        this.estado = estado;
    }

    public StringFilter getTelefone() {
        return telefone;
    }

    public void setTelefone(StringFilter telefone) {
        this.telefone = telefone;
    }

    public LongFilter getIdId() {
        return idId;
    }

    public void setIdId(LongFilter idId) {
        this.idId = idId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LareiraCriteria that = (LareiraCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(endereco, that.endereco) &&
            Objects.equals(bairro, that.bairro) &&
            Objects.equals(cep, that.cep) &&
            Objects.equals(cidade, that.cidade) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(telefone, that.telefone) &&
            Objects.equals(idId, that.idId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        endereco,
        bairro,
        cep,
        cidade,
        estado,
        telefone,
        idId
        );
    }

    @Override
    public String toString() {
        return "LareiraCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (endereco != null ? "endereco=" + endereco + ", " : "") +
                (bairro != null ? "bairro=" + bairro + ", " : "") +
                (cep != null ? "cep=" + cep + ", " : "") +
                (cidade != null ? "cidade=" + cidade + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (telefone != null ? "telefone=" + telefone + ", " : "") +
                (idId != null ? "idId=" + idId + ", " : "") +
            "}";
    }

}
