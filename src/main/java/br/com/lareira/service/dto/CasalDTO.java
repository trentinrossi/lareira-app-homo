package br.com.lareira.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import br.com.lareira.domain.enumeration.UF;

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

    private String casalFoneFixo;

    private String casalFoneContato;

    private String casalCep;

    private String casalNomeRua;

    private String casalNumeroRua;

    private String casalBairro;

    private String casalCidade;

    private UF casalEstado;

    @Lob
    private byte[] fotoCasal;

    private String fotoCasalContentType;
    private LocalDate dataUniao;

    private Integer numeroFicha;

    private String informacoesCasal;


    private Long lareiraId;

    private Long tipoUniaoId;

    private Long casalPadrinhoId;
    
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

    public String getCasalFoneFixo() {
        return casalFoneFixo;
    }

    public void setCasalFoneFixo(String casalFoneFixo) {
        this.casalFoneFixo = casalFoneFixo;
    }

    public String getCasalFoneContato() {
        return casalFoneContato;
    }

    public void setCasalFoneContato(String casalFoneContato) {
        this.casalFoneContato = casalFoneContato;
    }

    public String getCasalCep() {
        return casalCep;
    }

    public void setCasalCep(String casalCep) {
        this.casalCep = casalCep;
    }

    public String getCasalNomeRua() {
        return casalNomeRua;
    }

    public void setCasalNomeRua(String casalNomeRua) {
        this.casalNomeRua = casalNomeRua;
    }

    public String getCasalNumeroRua() {
        return casalNumeroRua;
    }

    public void setCasalNumeroRua(String casalNumeroRua) {
        this.casalNumeroRua = casalNumeroRua;
    }

    public String getCasalBairro() {
        return casalBairro;
    }

    public void setCasalBairro(String casalBairro) {
        this.casalBairro = casalBairro;
    }

    public String getCasalCidade() {
        return casalCidade;
    }

    public void setCasalCidade(String casalCidade) {
        this.casalCidade = casalCidade;
    }

    public UF getCasalEstado() {
        return casalEstado;
    }

    public void setCasalEstado(UF casalEstado) {
        this.casalEstado = casalEstado;
    }

    public byte[] getFotoCasal() {
        return fotoCasal;
    }

    public void setFotoCasal(byte[] fotoCasal) {
        this.fotoCasal = fotoCasal;
    }

    public String getFotoCasalContentType() {
        return fotoCasalContentType;
    }

    public void setFotoCasalContentType(String fotoCasalContentType) {
        this.fotoCasalContentType = fotoCasalContentType;
    }

    public LocalDate getDataUniao() {
        return dataUniao;
    }

    public void setDataUniao(LocalDate dataUniao) {
        this.dataUniao = dataUniao;
    }

    public Integer getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(Integer numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getInformacoesCasal() {
        return informacoesCasal;
    }

    public void setInformacoesCasal(String informacoesCasal) {
        this.informacoesCasal = informacoesCasal;
    }

    public Long getLareiraId() {
        return lareiraId;
    }

    public void setLareiraId(Long lareiraId) {
        this.lareiraId = lareiraId;
    }

    public Long getTipoUniaoId() {
        return tipoUniaoId;
    }

    public void setTipoUniaoId(Long tipoUniaoId) {
        this.tipoUniaoId = tipoUniaoId;
    }

    public Long getCasalPadrinhoId() {
        return casalPadrinhoId;
    }

    public void setCasalPadrinhoId(Long casalId) {
        this.casalPadrinhoId = casalId;
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
            ", casalFoneFixo='" + getCasalFoneFixo() + "'" +
            ", casalFoneContato='" + getCasalFoneContato() + "'" +
            ", casalCep='" + getCasalCep() + "'" +
            ", casalNomeRua='" + getCasalNomeRua() + "'" +
            ", casalNumeroRua='" + getCasalNumeroRua() + "'" +
            ", casalBairro='" + getCasalBairro() + "'" +
            ", casalCidade='" + getCasalCidade() + "'" +
            ", casalEstado='" + getCasalEstado() + "'" +
            ", fotoCasal='" + getFotoCasal() + "'" +
            ", dataUniao='" + getDataUniao() + "'" +
            ", numeroFicha=" + getNumeroFicha() +
            ", informacoesCasal='" + getInformacoesCasal() + "'" +
            ", lareiraId=" + getLareiraId() +
            ", tipoUniaoId=" + getTipoUniaoId() +
            ", casalPadrinhoId=" + getCasalPadrinhoId() +
            "}";
    }
}
