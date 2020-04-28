package br.com.lareira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.lareira.domain.enumeration.UF;

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

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "marido_nome", length = 100, nullable = false)
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

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "esposa_nome", length = 100, nullable = false)
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

    @Column(name = "casal_fone_fixo")
    private String casalFoneFixo;

    @Column(name = "casal_fone_contato")
    private String casalFoneContato;

    @Column(name = "casal_cep")
    private String casalCep;

    @Column(name = "casal_nome_rua")
    private String casalNomeRua;

    @Column(name = "casal_numero_rua")
    private String casalNumeroRua;

    @Column(name = "casal_bairro")
    private String casalBairro;

    @Column(name = "casal_cidade")
    private String casalCidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "casal_estado")
    private UF casalEstado;

    @Lob
    @Column(name = "foto_casal")
    private byte[] fotoCasal;

    @Column(name = "foto_casal_content_type")
    private String fotoCasalContentType;

    @Column(name = "data_uniao")
    private LocalDate dataUniao;

    @Column(name = "numero_ficha")
    private Integer numeroFicha;

    @Column(name = "informacoes_casal")
    private String informacoesCasal;

    @OneToMany(mappedBy = "casal")
    private Set<Filho> filhos = new HashSet<>();

    @OneToMany(mappedBy = "casalPadrinho")
    private Set<Casal> apadrinhados = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("casals")
    private Lareira lareira;

    @ManyToOne
    @JsonIgnoreProperties("casals")
    private TipoUniao tipoUniao;

    @ManyToOne
    @JsonIgnoreProperties("apadrinhados")
    private Casal casalPadrinho;

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

    public String getCasalFoneFixo() {
        return casalFoneFixo;
    }

    public Casal casalFoneFixo(String casalFoneFixo) {
        this.casalFoneFixo = casalFoneFixo;
        return this;
    }

    public void setCasalFoneFixo(String casalFoneFixo) {
        this.casalFoneFixo = casalFoneFixo;
    }

    public String getCasalFoneContato() {
        return casalFoneContato;
    }

    public Casal casalFoneContato(String casalFoneContato) {
        this.casalFoneContato = casalFoneContato;
        return this;
    }

    public void setCasalFoneContato(String casalFoneContato) {
        this.casalFoneContato = casalFoneContato;
    }

    public String getCasalCep() {
        return casalCep;
    }

    public Casal casalCep(String casalCep) {
        this.casalCep = casalCep;
        return this;
    }

    public void setCasalCep(String casalCep) {
        this.casalCep = casalCep;
    }

    public String getCasalNomeRua() {
        return casalNomeRua;
    }

    public Casal casalNomeRua(String casalNomeRua) {
        this.casalNomeRua = casalNomeRua;
        return this;
    }

    public void setCasalNomeRua(String casalNomeRua) {
        this.casalNomeRua = casalNomeRua;
    }

    public String getCasalNumeroRua() {
        return casalNumeroRua;
    }

    public Casal casalNumeroRua(String casalNumeroRua) {
        this.casalNumeroRua = casalNumeroRua;
        return this;
    }

    public void setCasalNumeroRua(String casalNumeroRua) {
        this.casalNumeroRua = casalNumeroRua;
    }

    public String getCasalBairro() {
        return casalBairro;
    }

    public Casal casalBairro(String casalBairro) {
        this.casalBairro = casalBairro;
        return this;
    }

    public void setCasalBairro(String casalBairro) {
        this.casalBairro = casalBairro;
    }

    public String getCasalCidade() {
        return casalCidade;
    }

    public Casal casalCidade(String casalCidade) {
        this.casalCidade = casalCidade;
        return this;
    }

    public void setCasalCidade(String casalCidade) {
        this.casalCidade = casalCidade;
    }

    public UF getCasalEstado() {
        return casalEstado;
    }

    public Casal casalEstado(UF casalEstado) {
        this.casalEstado = casalEstado;
        return this;
    }

    public void setCasalEstado(UF casalEstado) {
        this.casalEstado = casalEstado;
    }

    public byte[] getFotoCasal() {
        return fotoCasal;
    }

    public Casal fotoCasal(byte[] fotoCasal) {
        this.fotoCasal = fotoCasal;
        return this;
    }

    public void setFotoCasal(byte[] fotoCasal) {
        this.fotoCasal = fotoCasal;
    }

    public String getFotoCasalContentType() {
        return fotoCasalContentType;
    }

    public Casal fotoCasalContentType(String fotoCasalContentType) {
        this.fotoCasalContentType = fotoCasalContentType;
        return this;
    }

    public void setFotoCasalContentType(String fotoCasalContentType) {
        this.fotoCasalContentType = fotoCasalContentType;
    }

    public LocalDate getDataUniao() {
        return dataUniao;
    }

    public Casal dataUniao(LocalDate dataUniao) {
        this.dataUniao = dataUniao;
        return this;
    }

    public void setDataUniao(LocalDate dataUniao) {
        this.dataUniao = dataUniao;
    }

    public Integer getNumeroFicha() {
        return numeroFicha;
    }

    public Casal numeroFicha(Integer numeroFicha) {
        this.numeroFicha = numeroFicha;
        return this;
    }

    public void setNumeroFicha(Integer numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getInformacoesCasal() {
        return informacoesCasal;
    }

    public Casal informacoesCasal(String informacoesCasal) {
        this.informacoesCasal = informacoesCasal;
        return this;
    }

    public void setInformacoesCasal(String informacoesCasal) {
        this.informacoesCasal = informacoesCasal;
    }

    public Set<Filho> getFilhos() {
        return filhos;
    }

    public Casal filhos(Set<Filho> filhos) {
        this.filhos = filhos;
        return this;
    }

    public Casal addFilho(Filho filho) {
        this.filhos.add(filho);
        filho.setCasal(this);
        return this;
    }

    public Casal removeFilho(Filho filho) {
        this.filhos.remove(filho);
        filho.setCasal(null);
        return this;
    }

    public void setFilhos(Set<Filho> filhos) {
        this.filhos = filhos;
    }

    public Set<Casal> getApadrinhados() {
        return apadrinhados;
    }

    public Casal apadrinhados(Set<Casal> casals) {
        this.apadrinhados = casals;
        return this;
    }

    public Casal addApadrinhado(Casal casal) {
        this.apadrinhados.add(casal);
        casal.setCasalPadrinho(this);
        return this;
    }

    public Casal removeApadrinhado(Casal casal) {
        this.apadrinhados.remove(casal);
        casal.setCasalPadrinho(null);
        return this;
    }

    public void setApadrinhados(Set<Casal> casals) {
        this.apadrinhados = casals;
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

    public TipoUniao getTipoUniao() {
        return tipoUniao;
    }

    public Casal tipoUniao(TipoUniao tipoUniao) {
        this.tipoUniao = tipoUniao;
        return this;
    }

    public void setTipoUniao(TipoUniao tipoUniao) {
        this.tipoUniao = tipoUniao;
    }

    public Casal getCasalPadrinho() {
        return casalPadrinho;
    }

    public Casal casalPadrinho(Casal casal) {
        this.casalPadrinho = casal;
        return this;
    }

    public void setCasalPadrinho(Casal casal) {
        this.casalPadrinho = casal;
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
            ", casalFoneFixo='" + getCasalFoneFixo() + "'" +
            ", casalFoneContato='" + getCasalFoneContato() + "'" +
            ", casalCep='" + getCasalCep() + "'" +
            ", casalNomeRua='" + getCasalNomeRua() + "'" +
            ", casalNumeroRua='" + getCasalNumeroRua() + "'" +
            ", casalBairro='" + getCasalBairro() + "'" +
            ", casalCidade='" + getCasalCidade() + "'" +
            ", casalEstado='" + getCasalEstado() + "'" +
            ", fotoCasal='" + getFotoCasal() + "'" +
            ", fotoCasalContentType='" + getFotoCasalContentType() + "'" +
            ", dataUniao='" + getDataUniao() + "'" +
            ", numeroFicha=" + getNumeroFicha() +
            ", informacoesCasal='" + getInformacoesCasal() + "'" +
            "}";
    }
}
