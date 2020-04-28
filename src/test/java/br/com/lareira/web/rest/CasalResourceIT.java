package br.com.lareira.web.rest;

import br.com.lareira.LareiraAppHomoApp;
import br.com.lareira.domain.Casal;
import br.com.lareira.domain.Filho;
import br.com.lareira.domain.Casal;
import br.com.lareira.domain.Lareira;
import br.com.lareira.domain.TipoUniao;
import br.com.lareira.repository.CasalRepository;
import br.com.lareira.service.CasalService;
import br.com.lareira.service.dto.CasalDTO;
import br.com.lareira.service.mapper.CasalMapper;
import br.com.lareira.service.dto.CasalCriteria;
import br.com.lareira.service.CasalQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.lareira.domain.enumeration.UF;
/**
 * Integration tests for the {@link CasalResource} REST controller.
 */
@SpringBootTest(classes = LareiraAppHomoApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CasalResourceIT {

    private static final String DEFAULT_MARIDO_NOME = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_MARIDO_SOBRENOME = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_SOBRENOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MARIDO_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MARIDO_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_MARIDO_DATA_NASCIMENTO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_MARIDO_PROFISSAO = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_PROFISSAO = "BBBBBBBBBB";

    private static final String DEFAULT_MARIDO_TEL_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_TEL_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_MARIDO_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MARIDO_PROBLEMA_SAUDE = "AAAAAAAAAA";
    private static final String UPDATED_MARIDO_PROBLEMA_SAUDE = "BBBBBBBBBB";

    private static final String DEFAULT_ESPOSA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_ESPOSA_SOBRENOME = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_SOBRENOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ESPOSA_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ESPOSA_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_ESPOSA_DATA_NASCIMENTO = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ESPOSA_PROFISSAO = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_PROFISSAO = "BBBBBBBBBB";

    private static final String DEFAULT_ESPOSA_TEL_CELULAR = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_TEL_CELULAR = "BBBBBBBBBB";

    private static final String DEFAULT_ESPOSA_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ESPOSA_PROBLEMA_SAUDE = "AAAAAAAAAA";
    private static final String UPDATED_ESPOSA_PROBLEMA_SAUDE = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_FONE_FIXO = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_FONE_FIXO = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_FONE_CONTATO = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_FONE_CONTATO = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_NOME_RUA = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_NOME_RUA = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_NUMERO_RUA = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_NUMERO_RUA = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CASAL_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CASAL_CIDADE = "BBBBBBBBBB";

    private static final UF DEFAULT_CASAL_ESTADO = UF.AC;
    private static final UF UPDATED_CASAL_ESTADO = UF.AL;

    private static final byte[] DEFAULT_FOTO_CASAL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FOTO_CASAL = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FOTO_CASAL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FOTO_CASAL_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_DATA_UNIAO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_UNIAO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATA_UNIAO = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NUMERO_FICHA = 1;
    private static final Integer UPDATED_NUMERO_FICHA = 2;
    private static final Integer SMALLER_NUMERO_FICHA = 1 - 1;

    private static final String DEFAULT_INFORMACOES_CASAL = "AAAAAAAAAA";
    private static final String UPDATED_INFORMACOES_CASAL = "BBBBBBBBBB";

    @Autowired
    private CasalRepository casalRepository;

    @Autowired
    private CasalMapper casalMapper;

    @Autowired
    private CasalService casalService;

    @Autowired
    private CasalQueryService casalQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCasalMockMvc;

    private Casal casal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casal createEntity(EntityManager em) {
        Casal casal = new Casal()
            .maridoNome(DEFAULT_MARIDO_NOME)
            .maridoSobrenome(DEFAULT_MARIDO_SOBRENOME)
            .maridoDataNascimento(DEFAULT_MARIDO_DATA_NASCIMENTO)
            .maridoProfissao(DEFAULT_MARIDO_PROFISSAO)
            .maridoTelCelular(DEFAULT_MARIDO_TEL_CELULAR)
            .maridoEmail(DEFAULT_MARIDO_EMAIL)
            .maridoProblemaSaude(DEFAULT_MARIDO_PROBLEMA_SAUDE)
            .esposaNome(DEFAULT_ESPOSA_NOME)
            .esposaSobrenome(DEFAULT_ESPOSA_SOBRENOME)
            .esposaDataNascimento(DEFAULT_ESPOSA_DATA_NASCIMENTO)
            .esposaProfissao(DEFAULT_ESPOSA_PROFISSAO)
            .esposaTelCelular(DEFAULT_ESPOSA_TEL_CELULAR)
            .esposaEmail(DEFAULT_ESPOSA_EMAIL)
            .esposaProblemaSaude(DEFAULT_ESPOSA_PROBLEMA_SAUDE)
            .casalFoneFixo(DEFAULT_CASAL_FONE_FIXO)
            .casalFoneContato(DEFAULT_CASAL_FONE_CONTATO)
            .casalCep(DEFAULT_CASAL_CEP)
            .casalNomeRua(DEFAULT_CASAL_NOME_RUA)
            .casalNumeroRua(DEFAULT_CASAL_NUMERO_RUA)
            .casalBairro(DEFAULT_CASAL_BAIRRO)
            .casalCidade(DEFAULT_CASAL_CIDADE)
            .casalEstado(DEFAULT_CASAL_ESTADO)
            .fotoCasal(DEFAULT_FOTO_CASAL)
            .fotoCasalContentType(DEFAULT_FOTO_CASAL_CONTENT_TYPE)
            .dataUniao(DEFAULT_DATA_UNIAO)
            .numeroFicha(DEFAULT_NUMERO_FICHA)
            .informacoesCasal(DEFAULT_INFORMACOES_CASAL);
        // Add required entity
        Lareira lareira;
        if (TestUtil.findAll(em, Lareira.class).isEmpty()) {
            lareira = LareiraResourceIT.createEntity(em);
            em.persist(lareira);
            em.flush();
        } else {
            lareira = TestUtil.findAll(em, Lareira.class).get(0);
        }
        casal.setLareira(lareira);
        return casal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Casal createUpdatedEntity(EntityManager em) {
        Casal casal = new Casal()
            .maridoNome(UPDATED_MARIDO_NOME)
            .maridoSobrenome(UPDATED_MARIDO_SOBRENOME)
            .maridoDataNascimento(UPDATED_MARIDO_DATA_NASCIMENTO)
            .maridoProfissao(UPDATED_MARIDO_PROFISSAO)
            .maridoTelCelular(UPDATED_MARIDO_TEL_CELULAR)
            .maridoEmail(UPDATED_MARIDO_EMAIL)
            .maridoProblemaSaude(UPDATED_MARIDO_PROBLEMA_SAUDE)
            .esposaNome(UPDATED_ESPOSA_NOME)
            .esposaSobrenome(UPDATED_ESPOSA_SOBRENOME)
            .esposaDataNascimento(UPDATED_ESPOSA_DATA_NASCIMENTO)
            .esposaProfissao(UPDATED_ESPOSA_PROFISSAO)
            .esposaTelCelular(UPDATED_ESPOSA_TEL_CELULAR)
            .esposaEmail(UPDATED_ESPOSA_EMAIL)
            .esposaProblemaSaude(UPDATED_ESPOSA_PROBLEMA_SAUDE)
            .casalFoneFixo(UPDATED_CASAL_FONE_FIXO)
            .casalFoneContato(UPDATED_CASAL_FONE_CONTATO)
            .casalCep(UPDATED_CASAL_CEP)
            .casalNomeRua(UPDATED_CASAL_NOME_RUA)
            .casalNumeroRua(UPDATED_CASAL_NUMERO_RUA)
            .casalBairro(UPDATED_CASAL_BAIRRO)
            .casalCidade(UPDATED_CASAL_CIDADE)
            .casalEstado(UPDATED_CASAL_ESTADO)
            .fotoCasal(UPDATED_FOTO_CASAL)
            .fotoCasalContentType(UPDATED_FOTO_CASAL_CONTENT_TYPE)
            .dataUniao(UPDATED_DATA_UNIAO)
            .numeroFicha(UPDATED_NUMERO_FICHA)
            .informacoesCasal(UPDATED_INFORMACOES_CASAL);
        // Add required entity
        Lareira lareira;
        if (TestUtil.findAll(em, Lareira.class).isEmpty()) {
            lareira = LareiraResourceIT.createUpdatedEntity(em);
            em.persist(lareira);
            em.flush();
        } else {
            lareira = TestUtil.findAll(em, Lareira.class).get(0);
        }
        casal.setLareira(lareira);
        return casal;
    }

    @BeforeEach
    public void initTest() {
        casal = createEntity(em);
    }

    @Test
    @Transactional
    public void createCasal() throws Exception {
        int databaseSizeBeforeCreate = casalRepository.findAll().size();

        // Create the Casal
        CasalDTO casalDTO = casalMapper.toDto(casal);
        restCasalMockMvc.perform(post("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isCreated());

        // Validate the Casal in the database
        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeCreate + 1);
        Casal testCasal = casalList.get(casalList.size() - 1);
        assertThat(testCasal.getMaridoNome()).isEqualTo(DEFAULT_MARIDO_NOME);
        assertThat(testCasal.getMaridoSobrenome()).isEqualTo(DEFAULT_MARIDO_SOBRENOME);
        assertThat(testCasal.getMaridoDataNascimento()).isEqualTo(DEFAULT_MARIDO_DATA_NASCIMENTO);
        assertThat(testCasal.getMaridoProfissao()).isEqualTo(DEFAULT_MARIDO_PROFISSAO);
        assertThat(testCasal.getMaridoTelCelular()).isEqualTo(DEFAULT_MARIDO_TEL_CELULAR);
        assertThat(testCasal.getMaridoEmail()).isEqualTo(DEFAULT_MARIDO_EMAIL);
        assertThat(testCasal.getMaridoProblemaSaude()).isEqualTo(DEFAULT_MARIDO_PROBLEMA_SAUDE);
        assertThat(testCasal.getEsposaNome()).isEqualTo(DEFAULT_ESPOSA_NOME);
        assertThat(testCasal.getEsposaSobrenome()).isEqualTo(DEFAULT_ESPOSA_SOBRENOME);
        assertThat(testCasal.getEsposaDataNascimento()).isEqualTo(DEFAULT_ESPOSA_DATA_NASCIMENTO);
        assertThat(testCasal.getEsposaProfissao()).isEqualTo(DEFAULT_ESPOSA_PROFISSAO);
        assertThat(testCasal.getEsposaTelCelular()).isEqualTo(DEFAULT_ESPOSA_TEL_CELULAR);
        assertThat(testCasal.getEsposaEmail()).isEqualTo(DEFAULT_ESPOSA_EMAIL);
        assertThat(testCasal.getEsposaProblemaSaude()).isEqualTo(DEFAULT_ESPOSA_PROBLEMA_SAUDE);
        assertThat(testCasal.getCasalFoneFixo()).isEqualTo(DEFAULT_CASAL_FONE_FIXO);
        assertThat(testCasal.getCasalFoneContato()).isEqualTo(DEFAULT_CASAL_FONE_CONTATO);
        assertThat(testCasal.getCasalCep()).isEqualTo(DEFAULT_CASAL_CEP);
        assertThat(testCasal.getCasalNomeRua()).isEqualTo(DEFAULT_CASAL_NOME_RUA);
        assertThat(testCasal.getCasalNumeroRua()).isEqualTo(DEFAULT_CASAL_NUMERO_RUA);
        assertThat(testCasal.getCasalBairro()).isEqualTo(DEFAULT_CASAL_BAIRRO);
        assertThat(testCasal.getCasalCidade()).isEqualTo(DEFAULT_CASAL_CIDADE);
        assertThat(testCasal.getCasalEstado()).isEqualTo(DEFAULT_CASAL_ESTADO);
        assertThat(testCasal.getFotoCasal()).isEqualTo(DEFAULT_FOTO_CASAL);
        assertThat(testCasal.getFotoCasalContentType()).isEqualTo(DEFAULT_FOTO_CASAL_CONTENT_TYPE);
        assertThat(testCasal.getDataUniao()).isEqualTo(DEFAULT_DATA_UNIAO);
        assertThat(testCasal.getNumeroFicha()).isEqualTo(DEFAULT_NUMERO_FICHA);
        assertThat(testCasal.getInformacoesCasal()).isEqualTo(DEFAULT_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void createCasalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = casalRepository.findAll().size();

        // Create the Casal with an existing ID
        casal.setId(1L);
        CasalDTO casalDTO = casalMapper.toDto(casal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCasalMockMvc.perform(post("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Casal in the database
        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaridoNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = casalRepository.findAll().size();
        // set the field null
        casal.setMaridoNome(null);

        // Create the Casal, which fails.
        CasalDTO casalDTO = casalMapper.toDto(casal);

        restCasalMockMvc.perform(post("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isBadRequest());

        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEsposaNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = casalRepository.findAll().size();
        // set the field null
        casal.setEsposaNome(null);

        // Create the Casal, which fails.
        CasalDTO casalDTO = casalMapper.toDto(casal);

        restCasalMockMvc.perform(post("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isBadRequest());

        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCasals() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList
        restCasalMockMvc.perform(get("/api/casals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casal.getId().intValue())))
            .andExpect(jsonPath("$.[*].maridoNome").value(hasItem(DEFAULT_MARIDO_NOME)))
            .andExpect(jsonPath("$.[*].maridoSobrenome").value(hasItem(DEFAULT_MARIDO_SOBRENOME)))
            .andExpect(jsonPath("$.[*].maridoDataNascimento").value(hasItem(DEFAULT_MARIDO_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].maridoProfissao").value(hasItem(DEFAULT_MARIDO_PROFISSAO)))
            .andExpect(jsonPath("$.[*].maridoTelCelular").value(hasItem(DEFAULT_MARIDO_TEL_CELULAR)))
            .andExpect(jsonPath("$.[*].maridoEmail").value(hasItem(DEFAULT_MARIDO_EMAIL)))
            .andExpect(jsonPath("$.[*].maridoProblemaSaude").value(hasItem(DEFAULT_MARIDO_PROBLEMA_SAUDE)))
            .andExpect(jsonPath("$.[*].esposaNome").value(hasItem(DEFAULT_ESPOSA_NOME)))
            .andExpect(jsonPath("$.[*].esposaSobrenome").value(hasItem(DEFAULT_ESPOSA_SOBRENOME)))
            .andExpect(jsonPath("$.[*].esposaDataNascimento").value(hasItem(DEFAULT_ESPOSA_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].esposaProfissao").value(hasItem(DEFAULT_ESPOSA_PROFISSAO)))
            .andExpect(jsonPath("$.[*].esposaTelCelular").value(hasItem(DEFAULT_ESPOSA_TEL_CELULAR)))
            .andExpect(jsonPath("$.[*].esposaEmail").value(hasItem(DEFAULT_ESPOSA_EMAIL)))
            .andExpect(jsonPath("$.[*].esposaProblemaSaude").value(hasItem(DEFAULT_ESPOSA_PROBLEMA_SAUDE)))
            .andExpect(jsonPath("$.[*].casalFoneFixo").value(hasItem(DEFAULT_CASAL_FONE_FIXO)))
            .andExpect(jsonPath("$.[*].casalFoneContato").value(hasItem(DEFAULT_CASAL_FONE_CONTATO)))
            .andExpect(jsonPath("$.[*].casalCep").value(hasItem(DEFAULT_CASAL_CEP)))
            .andExpect(jsonPath("$.[*].casalNomeRua").value(hasItem(DEFAULT_CASAL_NOME_RUA)))
            .andExpect(jsonPath("$.[*].casalNumeroRua").value(hasItem(DEFAULT_CASAL_NUMERO_RUA)))
            .andExpect(jsonPath("$.[*].casalBairro").value(hasItem(DEFAULT_CASAL_BAIRRO)))
            .andExpect(jsonPath("$.[*].casalCidade").value(hasItem(DEFAULT_CASAL_CIDADE)))
            .andExpect(jsonPath("$.[*].casalEstado").value(hasItem(DEFAULT_CASAL_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].fotoCasalContentType").value(hasItem(DEFAULT_FOTO_CASAL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoCasal").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_CASAL))))
            .andExpect(jsonPath("$.[*].dataUniao").value(hasItem(DEFAULT_DATA_UNIAO.toString())))
            .andExpect(jsonPath("$.[*].numeroFicha").value(hasItem(DEFAULT_NUMERO_FICHA)))
            .andExpect(jsonPath("$.[*].informacoesCasal").value(hasItem(DEFAULT_INFORMACOES_CASAL)));
    }
    
    @Test
    @Transactional
    public void getCasal() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get the casal
        restCasalMockMvc.perform(get("/api/casals/{id}", casal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(casal.getId().intValue()))
            .andExpect(jsonPath("$.maridoNome").value(DEFAULT_MARIDO_NOME))
            .andExpect(jsonPath("$.maridoSobrenome").value(DEFAULT_MARIDO_SOBRENOME))
            .andExpect(jsonPath("$.maridoDataNascimento").value(DEFAULT_MARIDO_DATA_NASCIMENTO.toString()))
            .andExpect(jsonPath("$.maridoProfissao").value(DEFAULT_MARIDO_PROFISSAO))
            .andExpect(jsonPath("$.maridoTelCelular").value(DEFAULT_MARIDO_TEL_CELULAR))
            .andExpect(jsonPath("$.maridoEmail").value(DEFAULT_MARIDO_EMAIL))
            .andExpect(jsonPath("$.maridoProblemaSaude").value(DEFAULT_MARIDO_PROBLEMA_SAUDE))
            .andExpect(jsonPath("$.esposaNome").value(DEFAULT_ESPOSA_NOME))
            .andExpect(jsonPath("$.esposaSobrenome").value(DEFAULT_ESPOSA_SOBRENOME))
            .andExpect(jsonPath("$.esposaDataNascimento").value(DEFAULT_ESPOSA_DATA_NASCIMENTO.toString()))
            .andExpect(jsonPath("$.esposaProfissao").value(DEFAULT_ESPOSA_PROFISSAO))
            .andExpect(jsonPath("$.esposaTelCelular").value(DEFAULT_ESPOSA_TEL_CELULAR))
            .andExpect(jsonPath("$.esposaEmail").value(DEFAULT_ESPOSA_EMAIL))
            .andExpect(jsonPath("$.esposaProblemaSaude").value(DEFAULT_ESPOSA_PROBLEMA_SAUDE))
            .andExpect(jsonPath("$.casalFoneFixo").value(DEFAULT_CASAL_FONE_FIXO))
            .andExpect(jsonPath("$.casalFoneContato").value(DEFAULT_CASAL_FONE_CONTATO))
            .andExpect(jsonPath("$.casalCep").value(DEFAULT_CASAL_CEP))
            .andExpect(jsonPath("$.casalNomeRua").value(DEFAULT_CASAL_NOME_RUA))
            .andExpect(jsonPath("$.casalNumeroRua").value(DEFAULT_CASAL_NUMERO_RUA))
            .andExpect(jsonPath("$.casalBairro").value(DEFAULT_CASAL_BAIRRO))
            .andExpect(jsonPath("$.casalCidade").value(DEFAULT_CASAL_CIDADE))
            .andExpect(jsonPath("$.casalEstado").value(DEFAULT_CASAL_ESTADO.toString()))
            .andExpect(jsonPath("$.fotoCasalContentType").value(DEFAULT_FOTO_CASAL_CONTENT_TYPE))
            .andExpect(jsonPath("$.fotoCasal").value(Base64Utils.encodeToString(DEFAULT_FOTO_CASAL)))
            .andExpect(jsonPath("$.dataUniao").value(DEFAULT_DATA_UNIAO.toString()))
            .andExpect(jsonPath("$.numeroFicha").value(DEFAULT_NUMERO_FICHA))
            .andExpect(jsonPath("$.informacoesCasal").value(DEFAULT_INFORMACOES_CASAL));
    }


    @Test
    @Transactional
    public void getCasalsByIdFiltering() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        Long id = casal.getId();

        defaultCasalShouldBeFound("id.equals=" + id);
        defaultCasalShouldNotBeFound("id.notEquals=" + id);

        defaultCasalShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCasalShouldNotBeFound("id.greaterThan=" + id);

        defaultCasalShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCasalShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome equals to DEFAULT_MARIDO_NOME
        defaultCasalShouldBeFound("maridoNome.equals=" + DEFAULT_MARIDO_NOME);

        // Get all the casalList where maridoNome equals to UPDATED_MARIDO_NOME
        defaultCasalShouldNotBeFound("maridoNome.equals=" + UPDATED_MARIDO_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome not equals to DEFAULT_MARIDO_NOME
        defaultCasalShouldNotBeFound("maridoNome.notEquals=" + DEFAULT_MARIDO_NOME);

        // Get all the casalList where maridoNome not equals to UPDATED_MARIDO_NOME
        defaultCasalShouldBeFound("maridoNome.notEquals=" + UPDATED_MARIDO_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoNomeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome in DEFAULT_MARIDO_NOME or UPDATED_MARIDO_NOME
        defaultCasalShouldBeFound("maridoNome.in=" + DEFAULT_MARIDO_NOME + "," + UPDATED_MARIDO_NOME);

        // Get all the casalList where maridoNome equals to UPDATED_MARIDO_NOME
        defaultCasalShouldNotBeFound("maridoNome.in=" + UPDATED_MARIDO_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome is not null
        defaultCasalShouldBeFound("maridoNome.specified=true");

        // Get all the casalList where maridoNome is null
        defaultCasalShouldNotBeFound("maridoNome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoNomeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome contains DEFAULT_MARIDO_NOME
        defaultCasalShouldBeFound("maridoNome.contains=" + DEFAULT_MARIDO_NOME);

        // Get all the casalList where maridoNome contains UPDATED_MARIDO_NOME
        defaultCasalShouldNotBeFound("maridoNome.contains=" + UPDATED_MARIDO_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoNomeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoNome does not contain DEFAULT_MARIDO_NOME
        defaultCasalShouldNotBeFound("maridoNome.doesNotContain=" + DEFAULT_MARIDO_NOME);

        // Get all the casalList where maridoNome does not contain UPDATED_MARIDO_NOME
        defaultCasalShouldBeFound("maridoNome.doesNotContain=" + UPDATED_MARIDO_NOME);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome equals to DEFAULT_MARIDO_SOBRENOME
        defaultCasalShouldBeFound("maridoSobrenome.equals=" + DEFAULT_MARIDO_SOBRENOME);

        // Get all the casalList where maridoSobrenome equals to UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldNotBeFound("maridoSobrenome.equals=" + UPDATED_MARIDO_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome not equals to DEFAULT_MARIDO_SOBRENOME
        defaultCasalShouldNotBeFound("maridoSobrenome.notEquals=" + DEFAULT_MARIDO_SOBRENOME);

        // Get all the casalList where maridoSobrenome not equals to UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldBeFound("maridoSobrenome.notEquals=" + UPDATED_MARIDO_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome in DEFAULT_MARIDO_SOBRENOME or UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldBeFound("maridoSobrenome.in=" + DEFAULT_MARIDO_SOBRENOME + "," + UPDATED_MARIDO_SOBRENOME);

        // Get all the casalList where maridoSobrenome equals to UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldNotBeFound("maridoSobrenome.in=" + UPDATED_MARIDO_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome is not null
        defaultCasalShouldBeFound("maridoSobrenome.specified=true");

        // Get all the casalList where maridoSobrenome is null
        defaultCasalShouldNotBeFound("maridoSobrenome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome contains DEFAULT_MARIDO_SOBRENOME
        defaultCasalShouldBeFound("maridoSobrenome.contains=" + DEFAULT_MARIDO_SOBRENOME);

        // Get all the casalList where maridoSobrenome contains UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldNotBeFound("maridoSobrenome.contains=" + UPDATED_MARIDO_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoSobrenomeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoSobrenome does not contain DEFAULT_MARIDO_SOBRENOME
        defaultCasalShouldNotBeFound("maridoSobrenome.doesNotContain=" + DEFAULT_MARIDO_SOBRENOME);

        // Get all the casalList where maridoSobrenome does not contain UPDATED_MARIDO_SOBRENOME
        defaultCasalShouldBeFound("maridoSobrenome.doesNotContain=" + UPDATED_MARIDO_SOBRENOME);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento equals to DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.equals=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento equals to UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.equals=" + UPDATED_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento not equals to DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.notEquals=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento not equals to UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.notEquals=" + UPDATED_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento in DEFAULT_MARIDO_DATA_NASCIMENTO or UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.in=" + DEFAULT_MARIDO_DATA_NASCIMENTO + "," + UPDATED_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento equals to UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.in=" + UPDATED_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento is not null
        defaultCasalShouldBeFound("maridoDataNascimento.specified=true");

        // Get all the casalList where maridoDataNascimento is null
        defaultCasalShouldNotBeFound("maridoDataNascimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento is greater than or equal to DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.greaterThanOrEqual=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento is greater than or equal to UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.greaterThanOrEqual=" + UPDATED_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento is less than or equal to DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.lessThanOrEqual=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento is less than or equal to SMALLER_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.lessThanOrEqual=" + SMALLER_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsLessThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento is less than DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.lessThan=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento is less than UPDATED_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.lessThan=" + UPDATED_MARIDO_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoDataNascimentoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoDataNascimento is greater than DEFAULT_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("maridoDataNascimento.greaterThan=" + DEFAULT_MARIDO_DATA_NASCIMENTO);

        // Get all the casalList where maridoDataNascimento is greater than SMALLER_MARIDO_DATA_NASCIMENTO
        defaultCasalShouldBeFound("maridoDataNascimento.greaterThan=" + SMALLER_MARIDO_DATA_NASCIMENTO);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao equals to DEFAULT_MARIDO_PROFISSAO
        defaultCasalShouldBeFound("maridoProfissao.equals=" + DEFAULT_MARIDO_PROFISSAO);

        // Get all the casalList where maridoProfissao equals to UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldNotBeFound("maridoProfissao.equals=" + UPDATED_MARIDO_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao not equals to DEFAULT_MARIDO_PROFISSAO
        defaultCasalShouldNotBeFound("maridoProfissao.notEquals=" + DEFAULT_MARIDO_PROFISSAO);

        // Get all the casalList where maridoProfissao not equals to UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldBeFound("maridoProfissao.notEquals=" + UPDATED_MARIDO_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao in DEFAULT_MARIDO_PROFISSAO or UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldBeFound("maridoProfissao.in=" + DEFAULT_MARIDO_PROFISSAO + "," + UPDATED_MARIDO_PROFISSAO);

        // Get all the casalList where maridoProfissao equals to UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldNotBeFound("maridoProfissao.in=" + UPDATED_MARIDO_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao is not null
        defaultCasalShouldBeFound("maridoProfissao.specified=true");

        // Get all the casalList where maridoProfissao is null
        defaultCasalShouldNotBeFound("maridoProfissao.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao contains DEFAULT_MARIDO_PROFISSAO
        defaultCasalShouldBeFound("maridoProfissao.contains=" + DEFAULT_MARIDO_PROFISSAO);

        // Get all the casalList where maridoProfissao contains UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldNotBeFound("maridoProfissao.contains=" + UPDATED_MARIDO_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProfissaoNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProfissao does not contain DEFAULT_MARIDO_PROFISSAO
        defaultCasalShouldNotBeFound("maridoProfissao.doesNotContain=" + DEFAULT_MARIDO_PROFISSAO);

        // Get all the casalList where maridoProfissao does not contain UPDATED_MARIDO_PROFISSAO
        defaultCasalShouldBeFound("maridoProfissao.doesNotContain=" + UPDATED_MARIDO_PROFISSAO);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular equals to DEFAULT_MARIDO_TEL_CELULAR
        defaultCasalShouldBeFound("maridoTelCelular.equals=" + DEFAULT_MARIDO_TEL_CELULAR);

        // Get all the casalList where maridoTelCelular equals to UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldNotBeFound("maridoTelCelular.equals=" + UPDATED_MARIDO_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular not equals to DEFAULT_MARIDO_TEL_CELULAR
        defaultCasalShouldNotBeFound("maridoTelCelular.notEquals=" + DEFAULT_MARIDO_TEL_CELULAR);

        // Get all the casalList where maridoTelCelular not equals to UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldBeFound("maridoTelCelular.notEquals=" + UPDATED_MARIDO_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular in DEFAULT_MARIDO_TEL_CELULAR or UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldBeFound("maridoTelCelular.in=" + DEFAULT_MARIDO_TEL_CELULAR + "," + UPDATED_MARIDO_TEL_CELULAR);

        // Get all the casalList where maridoTelCelular equals to UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldNotBeFound("maridoTelCelular.in=" + UPDATED_MARIDO_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular is not null
        defaultCasalShouldBeFound("maridoTelCelular.specified=true");

        // Get all the casalList where maridoTelCelular is null
        defaultCasalShouldNotBeFound("maridoTelCelular.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular contains DEFAULT_MARIDO_TEL_CELULAR
        defaultCasalShouldBeFound("maridoTelCelular.contains=" + DEFAULT_MARIDO_TEL_CELULAR);

        // Get all the casalList where maridoTelCelular contains UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldNotBeFound("maridoTelCelular.contains=" + UPDATED_MARIDO_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoTelCelularNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoTelCelular does not contain DEFAULT_MARIDO_TEL_CELULAR
        defaultCasalShouldNotBeFound("maridoTelCelular.doesNotContain=" + DEFAULT_MARIDO_TEL_CELULAR);

        // Get all the casalList where maridoTelCelular does not contain UPDATED_MARIDO_TEL_CELULAR
        defaultCasalShouldBeFound("maridoTelCelular.doesNotContain=" + UPDATED_MARIDO_TEL_CELULAR);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail equals to DEFAULT_MARIDO_EMAIL
        defaultCasalShouldBeFound("maridoEmail.equals=" + DEFAULT_MARIDO_EMAIL);

        // Get all the casalList where maridoEmail equals to UPDATED_MARIDO_EMAIL
        defaultCasalShouldNotBeFound("maridoEmail.equals=" + UPDATED_MARIDO_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail not equals to DEFAULT_MARIDO_EMAIL
        defaultCasalShouldNotBeFound("maridoEmail.notEquals=" + DEFAULT_MARIDO_EMAIL);

        // Get all the casalList where maridoEmail not equals to UPDATED_MARIDO_EMAIL
        defaultCasalShouldBeFound("maridoEmail.notEquals=" + UPDATED_MARIDO_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoEmailIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail in DEFAULT_MARIDO_EMAIL or UPDATED_MARIDO_EMAIL
        defaultCasalShouldBeFound("maridoEmail.in=" + DEFAULT_MARIDO_EMAIL + "," + UPDATED_MARIDO_EMAIL);

        // Get all the casalList where maridoEmail equals to UPDATED_MARIDO_EMAIL
        defaultCasalShouldNotBeFound("maridoEmail.in=" + UPDATED_MARIDO_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail is not null
        defaultCasalShouldBeFound("maridoEmail.specified=true");

        // Get all the casalList where maridoEmail is null
        defaultCasalShouldNotBeFound("maridoEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoEmailContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail contains DEFAULT_MARIDO_EMAIL
        defaultCasalShouldBeFound("maridoEmail.contains=" + DEFAULT_MARIDO_EMAIL);

        // Get all the casalList where maridoEmail contains UPDATED_MARIDO_EMAIL
        defaultCasalShouldNotBeFound("maridoEmail.contains=" + UPDATED_MARIDO_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoEmailNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoEmail does not contain DEFAULT_MARIDO_EMAIL
        defaultCasalShouldNotBeFound("maridoEmail.doesNotContain=" + DEFAULT_MARIDO_EMAIL);

        // Get all the casalList where maridoEmail does not contain UPDATED_MARIDO_EMAIL
        defaultCasalShouldBeFound("maridoEmail.doesNotContain=" + UPDATED_MARIDO_EMAIL);
    }


    @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude equals to DEFAULT_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("maridoProblemaSaude.equals=" + DEFAULT_MARIDO_PROBLEMA_SAUDE);

        // Get all the casalList where maridoProblemaSaude equals to UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("maridoProblemaSaude.equals=" + UPDATED_MARIDO_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude not equals to DEFAULT_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("maridoProblemaSaude.notEquals=" + DEFAULT_MARIDO_PROBLEMA_SAUDE);

        // Get all the casalList where maridoProblemaSaude not equals to UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("maridoProblemaSaude.notEquals=" + UPDATED_MARIDO_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude in DEFAULT_MARIDO_PROBLEMA_SAUDE or UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("maridoProblemaSaude.in=" + DEFAULT_MARIDO_PROBLEMA_SAUDE + "," + UPDATED_MARIDO_PROBLEMA_SAUDE);

        // Get all the casalList where maridoProblemaSaude equals to UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("maridoProblemaSaude.in=" + UPDATED_MARIDO_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude is not null
        defaultCasalShouldBeFound("maridoProblemaSaude.specified=true");

        // Get all the casalList where maridoProblemaSaude is null
        defaultCasalShouldNotBeFound("maridoProblemaSaude.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude contains DEFAULT_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("maridoProblemaSaude.contains=" + DEFAULT_MARIDO_PROBLEMA_SAUDE);

        // Get all the casalList where maridoProblemaSaude contains UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("maridoProblemaSaude.contains=" + UPDATED_MARIDO_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByMaridoProblemaSaudeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where maridoProblemaSaude does not contain DEFAULT_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("maridoProblemaSaude.doesNotContain=" + DEFAULT_MARIDO_PROBLEMA_SAUDE);

        // Get all the casalList where maridoProblemaSaude does not contain UPDATED_MARIDO_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("maridoProblemaSaude.doesNotContain=" + UPDATED_MARIDO_PROBLEMA_SAUDE);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome equals to DEFAULT_ESPOSA_NOME
        defaultCasalShouldBeFound("esposaNome.equals=" + DEFAULT_ESPOSA_NOME);

        // Get all the casalList where esposaNome equals to UPDATED_ESPOSA_NOME
        defaultCasalShouldNotBeFound("esposaNome.equals=" + UPDATED_ESPOSA_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome not equals to DEFAULT_ESPOSA_NOME
        defaultCasalShouldNotBeFound("esposaNome.notEquals=" + DEFAULT_ESPOSA_NOME);

        // Get all the casalList where esposaNome not equals to UPDATED_ESPOSA_NOME
        defaultCasalShouldBeFound("esposaNome.notEquals=" + UPDATED_ESPOSA_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaNomeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome in DEFAULT_ESPOSA_NOME or UPDATED_ESPOSA_NOME
        defaultCasalShouldBeFound("esposaNome.in=" + DEFAULT_ESPOSA_NOME + "," + UPDATED_ESPOSA_NOME);

        // Get all the casalList where esposaNome equals to UPDATED_ESPOSA_NOME
        defaultCasalShouldNotBeFound("esposaNome.in=" + UPDATED_ESPOSA_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome is not null
        defaultCasalShouldBeFound("esposaNome.specified=true");

        // Get all the casalList where esposaNome is null
        defaultCasalShouldNotBeFound("esposaNome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaNomeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome contains DEFAULT_ESPOSA_NOME
        defaultCasalShouldBeFound("esposaNome.contains=" + DEFAULT_ESPOSA_NOME);

        // Get all the casalList where esposaNome contains UPDATED_ESPOSA_NOME
        defaultCasalShouldNotBeFound("esposaNome.contains=" + UPDATED_ESPOSA_NOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaNomeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaNome does not contain DEFAULT_ESPOSA_NOME
        defaultCasalShouldNotBeFound("esposaNome.doesNotContain=" + DEFAULT_ESPOSA_NOME);

        // Get all the casalList where esposaNome does not contain UPDATED_ESPOSA_NOME
        defaultCasalShouldBeFound("esposaNome.doesNotContain=" + UPDATED_ESPOSA_NOME);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome equals to DEFAULT_ESPOSA_SOBRENOME
        defaultCasalShouldBeFound("esposaSobrenome.equals=" + DEFAULT_ESPOSA_SOBRENOME);

        // Get all the casalList where esposaSobrenome equals to UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldNotBeFound("esposaSobrenome.equals=" + UPDATED_ESPOSA_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome not equals to DEFAULT_ESPOSA_SOBRENOME
        defaultCasalShouldNotBeFound("esposaSobrenome.notEquals=" + DEFAULT_ESPOSA_SOBRENOME);

        // Get all the casalList where esposaSobrenome not equals to UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldBeFound("esposaSobrenome.notEquals=" + UPDATED_ESPOSA_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome in DEFAULT_ESPOSA_SOBRENOME or UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldBeFound("esposaSobrenome.in=" + DEFAULT_ESPOSA_SOBRENOME + "," + UPDATED_ESPOSA_SOBRENOME);

        // Get all the casalList where esposaSobrenome equals to UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldNotBeFound("esposaSobrenome.in=" + UPDATED_ESPOSA_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome is not null
        defaultCasalShouldBeFound("esposaSobrenome.specified=true");

        // Get all the casalList where esposaSobrenome is null
        defaultCasalShouldNotBeFound("esposaSobrenome.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome contains DEFAULT_ESPOSA_SOBRENOME
        defaultCasalShouldBeFound("esposaSobrenome.contains=" + DEFAULT_ESPOSA_SOBRENOME);

        // Get all the casalList where esposaSobrenome contains UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldNotBeFound("esposaSobrenome.contains=" + UPDATED_ESPOSA_SOBRENOME);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaSobrenomeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaSobrenome does not contain DEFAULT_ESPOSA_SOBRENOME
        defaultCasalShouldNotBeFound("esposaSobrenome.doesNotContain=" + DEFAULT_ESPOSA_SOBRENOME);

        // Get all the casalList where esposaSobrenome does not contain UPDATED_ESPOSA_SOBRENOME
        defaultCasalShouldBeFound("esposaSobrenome.doesNotContain=" + UPDATED_ESPOSA_SOBRENOME);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento equals to DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.equals=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento equals to UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.equals=" + UPDATED_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento not equals to DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.notEquals=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento not equals to UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.notEquals=" + UPDATED_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento in DEFAULT_ESPOSA_DATA_NASCIMENTO or UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.in=" + DEFAULT_ESPOSA_DATA_NASCIMENTO + "," + UPDATED_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento equals to UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.in=" + UPDATED_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento is not null
        defaultCasalShouldBeFound("esposaDataNascimento.specified=true");

        // Get all the casalList where esposaDataNascimento is null
        defaultCasalShouldNotBeFound("esposaDataNascimento.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento is greater than or equal to DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.greaterThanOrEqual=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento is greater than or equal to UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.greaterThanOrEqual=" + UPDATED_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento is less than or equal to DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.lessThanOrEqual=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento is less than or equal to SMALLER_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.lessThanOrEqual=" + SMALLER_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsLessThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento is less than DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.lessThan=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento is less than UPDATED_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.lessThan=" + UPDATED_ESPOSA_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaDataNascimentoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaDataNascimento is greater than DEFAULT_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldNotBeFound("esposaDataNascimento.greaterThan=" + DEFAULT_ESPOSA_DATA_NASCIMENTO);

        // Get all the casalList where esposaDataNascimento is greater than SMALLER_ESPOSA_DATA_NASCIMENTO
        defaultCasalShouldBeFound("esposaDataNascimento.greaterThan=" + SMALLER_ESPOSA_DATA_NASCIMENTO);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao equals to DEFAULT_ESPOSA_PROFISSAO
        defaultCasalShouldBeFound("esposaProfissao.equals=" + DEFAULT_ESPOSA_PROFISSAO);

        // Get all the casalList where esposaProfissao equals to UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldNotBeFound("esposaProfissao.equals=" + UPDATED_ESPOSA_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao not equals to DEFAULT_ESPOSA_PROFISSAO
        defaultCasalShouldNotBeFound("esposaProfissao.notEquals=" + DEFAULT_ESPOSA_PROFISSAO);

        // Get all the casalList where esposaProfissao not equals to UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldBeFound("esposaProfissao.notEquals=" + UPDATED_ESPOSA_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao in DEFAULT_ESPOSA_PROFISSAO or UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldBeFound("esposaProfissao.in=" + DEFAULT_ESPOSA_PROFISSAO + "," + UPDATED_ESPOSA_PROFISSAO);

        // Get all the casalList where esposaProfissao equals to UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldNotBeFound("esposaProfissao.in=" + UPDATED_ESPOSA_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao is not null
        defaultCasalShouldBeFound("esposaProfissao.specified=true");

        // Get all the casalList where esposaProfissao is null
        defaultCasalShouldNotBeFound("esposaProfissao.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao contains DEFAULT_ESPOSA_PROFISSAO
        defaultCasalShouldBeFound("esposaProfissao.contains=" + DEFAULT_ESPOSA_PROFISSAO);

        // Get all the casalList where esposaProfissao contains UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldNotBeFound("esposaProfissao.contains=" + UPDATED_ESPOSA_PROFISSAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProfissaoNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProfissao does not contain DEFAULT_ESPOSA_PROFISSAO
        defaultCasalShouldNotBeFound("esposaProfissao.doesNotContain=" + DEFAULT_ESPOSA_PROFISSAO);

        // Get all the casalList where esposaProfissao does not contain UPDATED_ESPOSA_PROFISSAO
        defaultCasalShouldBeFound("esposaProfissao.doesNotContain=" + UPDATED_ESPOSA_PROFISSAO);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular equals to DEFAULT_ESPOSA_TEL_CELULAR
        defaultCasalShouldBeFound("esposaTelCelular.equals=" + DEFAULT_ESPOSA_TEL_CELULAR);

        // Get all the casalList where esposaTelCelular equals to UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldNotBeFound("esposaTelCelular.equals=" + UPDATED_ESPOSA_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular not equals to DEFAULT_ESPOSA_TEL_CELULAR
        defaultCasalShouldNotBeFound("esposaTelCelular.notEquals=" + DEFAULT_ESPOSA_TEL_CELULAR);

        // Get all the casalList where esposaTelCelular not equals to UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldBeFound("esposaTelCelular.notEquals=" + UPDATED_ESPOSA_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular in DEFAULT_ESPOSA_TEL_CELULAR or UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldBeFound("esposaTelCelular.in=" + DEFAULT_ESPOSA_TEL_CELULAR + "," + UPDATED_ESPOSA_TEL_CELULAR);

        // Get all the casalList where esposaTelCelular equals to UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldNotBeFound("esposaTelCelular.in=" + UPDATED_ESPOSA_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular is not null
        defaultCasalShouldBeFound("esposaTelCelular.specified=true");

        // Get all the casalList where esposaTelCelular is null
        defaultCasalShouldNotBeFound("esposaTelCelular.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular contains DEFAULT_ESPOSA_TEL_CELULAR
        defaultCasalShouldBeFound("esposaTelCelular.contains=" + DEFAULT_ESPOSA_TEL_CELULAR);

        // Get all the casalList where esposaTelCelular contains UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldNotBeFound("esposaTelCelular.contains=" + UPDATED_ESPOSA_TEL_CELULAR);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaTelCelularNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaTelCelular does not contain DEFAULT_ESPOSA_TEL_CELULAR
        defaultCasalShouldNotBeFound("esposaTelCelular.doesNotContain=" + DEFAULT_ESPOSA_TEL_CELULAR);

        // Get all the casalList where esposaTelCelular does not contain UPDATED_ESPOSA_TEL_CELULAR
        defaultCasalShouldBeFound("esposaTelCelular.doesNotContain=" + UPDATED_ESPOSA_TEL_CELULAR);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail equals to DEFAULT_ESPOSA_EMAIL
        defaultCasalShouldBeFound("esposaEmail.equals=" + DEFAULT_ESPOSA_EMAIL);

        // Get all the casalList where esposaEmail equals to UPDATED_ESPOSA_EMAIL
        defaultCasalShouldNotBeFound("esposaEmail.equals=" + UPDATED_ESPOSA_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail not equals to DEFAULT_ESPOSA_EMAIL
        defaultCasalShouldNotBeFound("esposaEmail.notEquals=" + DEFAULT_ESPOSA_EMAIL);

        // Get all the casalList where esposaEmail not equals to UPDATED_ESPOSA_EMAIL
        defaultCasalShouldBeFound("esposaEmail.notEquals=" + UPDATED_ESPOSA_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaEmailIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail in DEFAULT_ESPOSA_EMAIL or UPDATED_ESPOSA_EMAIL
        defaultCasalShouldBeFound("esposaEmail.in=" + DEFAULT_ESPOSA_EMAIL + "," + UPDATED_ESPOSA_EMAIL);

        // Get all the casalList where esposaEmail equals to UPDATED_ESPOSA_EMAIL
        defaultCasalShouldNotBeFound("esposaEmail.in=" + UPDATED_ESPOSA_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail is not null
        defaultCasalShouldBeFound("esposaEmail.specified=true");

        // Get all the casalList where esposaEmail is null
        defaultCasalShouldNotBeFound("esposaEmail.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaEmailContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail contains DEFAULT_ESPOSA_EMAIL
        defaultCasalShouldBeFound("esposaEmail.contains=" + DEFAULT_ESPOSA_EMAIL);

        // Get all the casalList where esposaEmail contains UPDATED_ESPOSA_EMAIL
        defaultCasalShouldNotBeFound("esposaEmail.contains=" + UPDATED_ESPOSA_EMAIL);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaEmailNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaEmail does not contain DEFAULT_ESPOSA_EMAIL
        defaultCasalShouldNotBeFound("esposaEmail.doesNotContain=" + DEFAULT_ESPOSA_EMAIL);

        // Get all the casalList where esposaEmail does not contain UPDATED_ESPOSA_EMAIL
        defaultCasalShouldBeFound("esposaEmail.doesNotContain=" + UPDATED_ESPOSA_EMAIL);
    }


    @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude equals to DEFAULT_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("esposaProblemaSaude.equals=" + DEFAULT_ESPOSA_PROBLEMA_SAUDE);

        // Get all the casalList where esposaProblemaSaude equals to UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("esposaProblemaSaude.equals=" + UPDATED_ESPOSA_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude not equals to DEFAULT_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("esposaProblemaSaude.notEquals=" + DEFAULT_ESPOSA_PROBLEMA_SAUDE);

        // Get all the casalList where esposaProblemaSaude not equals to UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("esposaProblemaSaude.notEquals=" + UPDATED_ESPOSA_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude in DEFAULT_ESPOSA_PROBLEMA_SAUDE or UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("esposaProblemaSaude.in=" + DEFAULT_ESPOSA_PROBLEMA_SAUDE + "," + UPDATED_ESPOSA_PROBLEMA_SAUDE);

        // Get all the casalList where esposaProblemaSaude equals to UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("esposaProblemaSaude.in=" + UPDATED_ESPOSA_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude is not null
        defaultCasalShouldBeFound("esposaProblemaSaude.specified=true");

        // Get all the casalList where esposaProblemaSaude is null
        defaultCasalShouldNotBeFound("esposaProblemaSaude.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude contains DEFAULT_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("esposaProblemaSaude.contains=" + DEFAULT_ESPOSA_PROBLEMA_SAUDE);

        // Get all the casalList where esposaProblemaSaude contains UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("esposaProblemaSaude.contains=" + UPDATED_ESPOSA_PROBLEMA_SAUDE);
    }

    @Test
    @Transactional
    public void getAllCasalsByEsposaProblemaSaudeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where esposaProblemaSaude does not contain DEFAULT_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldNotBeFound("esposaProblemaSaude.doesNotContain=" + DEFAULT_ESPOSA_PROBLEMA_SAUDE);

        // Get all the casalList where esposaProblemaSaude does not contain UPDATED_ESPOSA_PROBLEMA_SAUDE
        defaultCasalShouldBeFound("esposaProblemaSaude.doesNotContain=" + UPDATED_ESPOSA_PROBLEMA_SAUDE);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo equals to DEFAULT_CASAL_FONE_FIXO
        defaultCasalShouldBeFound("casalFoneFixo.equals=" + DEFAULT_CASAL_FONE_FIXO);

        // Get all the casalList where casalFoneFixo equals to UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldNotBeFound("casalFoneFixo.equals=" + UPDATED_CASAL_FONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo not equals to DEFAULT_CASAL_FONE_FIXO
        defaultCasalShouldNotBeFound("casalFoneFixo.notEquals=" + DEFAULT_CASAL_FONE_FIXO);

        // Get all the casalList where casalFoneFixo not equals to UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldBeFound("casalFoneFixo.notEquals=" + UPDATED_CASAL_FONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo in DEFAULT_CASAL_FONE_FIXO or UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldBeFound("casalFoneFixo.in=" + DEFAULT_CASAL_FONE_FIXO + "," + UPDATED_CASAL_FONE_FIXO);

        // Get all the casalList where casalFoneFixo equals to UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldNotBeFound("casalFoneFixo.in=" + UPDATED_CASAL_FONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo is not null
        defaultCasalShouldBeFound("casalFoneFixo.specified=true");

        // Get all the casalList where casalFoneFixo is null
        defaultCasalShouldNotBeFound("casalFoneFixo.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo contains DEFAULT_CASAL_FONE_FIXO
        defaultCasalShouldBeFound("casalFoneFixo.contains=" + DEFAULT_CASAL_FONE_FIXO);

        // Get all the casalList where casalFoneFixo contains UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldNotBeFound("casalFoneFixo.contains=" + UPDATED_CASAL_FONE_FIXO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneFixoNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneFixo does not contain DEFAULT_CASAL_FONE_FIXO
        defaultCasalShouldNotBeFound("casalFoneFixo.doesNotContain=" + DEFAULT_CASAL_FONE_FIXO);

        // Get all the casalList where casalFoneFixo does not contain UPDATED_CASAL_FONE_FIXO
        defaultCasalShouldBeFound("casalFoneFixo.doesNotContain=" + UPDATED_CASAL_FONE_FIXO);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato equals to DEFAULT_CASAL_FONE_CONTATO
        defaultCasalShouldBeFound("casalFoneContato.equals=" + DEFAULT_CASAL_FONE_CONTATO);

        // Get all the casalList where casalFoneContato equals to UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldNotBeFound("casalFoneContato.equals=" + UPDATED_CASAL_FONE_CONTATO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato not equals to DEFAULT_CASAL_FONE_CONTATO
        defaultCasalShouldNotBeFound("casalFoneContato.notEquals=" + DEFAULT_CASAL_FONE_CONTATO);

        // Get all the casalList where casalFoneContato not equals to UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldBeFound("casalFoneContato.notEquals=" + UPDATED_CASAL_FONE_CONTATO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato in DEFAULT_CASAL_FONE_CONTATO or UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldBeFound("casalFoneContato.in=" + DEFAULT_CASAL_FONE_CONTATO + "," + UPDATED_CASAL_FONE_CONTATO);

        // Get all the casalList where casalFoneContato equals to UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldNotBeFound("casalFoneContato.in=" + UPDATED_CASAL_FONE_CONTATO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato is not null
        defaultCasalShouldBeFound("casalFoneContato.specified=true");

        // Get all the casalList where casalFoneContato is null
        defaultCasalShouldNotBeFound("casalFoneContato.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato contains DEFAULT_CASAL_FONE_CONTATO
        defaultCasalShouldBeFound("casalFoneContato.contains=" + DEFAULT_CASAL_FONE_CONTATO);

        // Get all the casalList where casalFoneContato contains UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldNotBeFound("casalFoneContato.contains=" + UPDATED_CASAL_FONE_CONTATO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalFoneContatoNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalFoneContato does not contain DEFAULT_CASAL_FONE_CONTATO
        defaultCasalShouldNotBeFound("casalFoneContato.doesNotContain=" + DEFAULT_CASAL_FONE_CONTATO);

        // Get all the casalList where casalFoneContato does not contain UPDATED_CASAL_FONE_CONTATO
        defaultCasalShouldBeFound("casalFoneContato.doesNotContain=" + UPDATED_CASAL_FONE_CONTATO);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalCepIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep equals to DEFAULT_CASAL_CEP
        defaultCasalShouldBeFound("casalCep.equals=" + DEFAULT_CASAL_CEP);

        // Get all the casalList where casalCep equals to UPDATED_CASAL_CEP
        defaultCasalShouldNotBeFound("casalCep.equals=" + UPDATED_CASAL_CEP);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep not equals to DEFAULT_CASAL_CEP
        defaultCasalShouldNotBeFound("casalCep.notEquals=" + DEFAULT_CASAL_CEP);

        // Get all the casalList where casalCep not equals to UPDATED_CASAL_CEP
        defaultCasalShouldBeFound("casalCep.notEquals=" + UPDATED_CASAL_CEP);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCepIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep in DEFAULT_CASAL_CEP or UPDATED_CASAL_CEP
        defaultCasalShouldBeFound("casalCep.in=" + DEFAULT_CASAL_CEP + "," + UPDATED_CASAL_CEP);

        // Get all the casalList where casalCep equals to UPDATED_CASAL_CEP
        defaultCasalShouldNotBeFound("casalCep.in=" + UPDATED_CASAL_CEP);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep is not null
        defaultCasalShouldBeFound("casalCep.specified=true");

        // Get all the casalList where casalCep is null
        defaultCasalShouldNotBeFound("casalCep.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalCepContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep contains DEFAULT_CASAL_CEP
        defaultCasalShouldBeFound("casalCep.contains=" + DEFAULT_CASAL_CEP);

        // Get all the casalList where casalCep contains UPDATED_CASAL_CEP
        defaultCasalShouldNotBeFound("casalCep.contains=" + UPDATED_CASAL_CEP);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCepNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCep does not contain DEFAULT_CASAL_CEP
        defaultCasalShouldNotBeFound("casalCep.doesNotContain=" + DEFAULT_CASAL_CEP);

        // Get all the casalList where casalCep does not contain UPDATED_CASAL_CEP
        defaultCasalShouldBeFound("casalCep.doesNotContain=" + UPDATED_CASAL_CEP);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua equals to DEFAULT_CASAL_NOME_RUA
        defaultCasalShouldBeFound("casalNomeRua.equals=" + DEFAULT_CASAL_NOME_RUA);

        // Get all the casalList where casalNomeRua equals to UPDATED_CASAL_NOME_RUA
        defaultCasalShouldNotBeFound("casalNomeRua.equals=" + UPDATED_CASAL_NOME_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua not equals to DEFAULT_CASAL_NOME_RUA
        defaultCasalShouldNotBeFound("casalNomeRua.notEquals=" + DEFAULT_CASAL_NOME_RUA);

        // Get all the casalList where casalNomeRua not equals to UPDATED_CASAL_NOME_RUA
        defaultCasalShouldBeFound("casalNomeRua.notEquals=" + UPDATED_CASAL_NOME_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua in DEFAULT_CASAL_NOME_RUA or UPDATED_CASAL_NOME_RUA
        defaultCasalShouldBeFound("casalNomeRua.in=" + DEFAULT_CASAL_NOME_RUA + "," + UPDATED_CASAL_NOME_RUA);

        // Get all the casalList where casalNomeRua equals to UPDATED_CASAL_NOME_RUA
        defaultCasalShouldNotBeFound("casalNomeRua.in=" + UPDATED_CASAL_NOME_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua is not null
        defaultCasalShouldBeFound("casalNomeRua.specified=true");

        // Get all the casalList where casalNomeRua is null
        defaultCasalShouldNotBeFound("casalNomeRua.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua contains DEFAULT_CASAL_NOME_RUA
        defaultCasalShouldBeFound("casalNomeRua.contains=" + DEFAULT_CASAL_NOME_RUA);

        // Get all the casalList where casalNomeRua contains UPDATED_CASAL_NOME_RUA
        defaultCasalShouldNotBeFound("casalNomeRua.contains=" + UPDATED_CASAL_NOME_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNomeRuaNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNomeRua does not contain DEFAULT_CASAL_NOME_RUA
        defaultCasalShouldNotBeFound("casalNomeRua.doesNotContain=" + DEFAULT_CASAL_NOME_RUA);

        // Get all the casalList where casalNomeRua does not contain UPDATED_CASAL_NOME_RUA
        defaultCasalShouldBeFound("casalNomeRua.doesNotContain=" + UPDATED_CASAL_NOME_RUA);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua equals to DEFAULT_CASAL_NUMERO_RUA
        defaultCasalShouldBeFound("casalNumeroRua.equals=" + DEFAULT_CASAL_NUMERO_RUA);

        // Get all the casalList where casalNumeroRua equals to UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldNotBeFound("casalNumeroRua.equals=" + UPDATED_CASAL_NUMERO_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua not equals to DEFAULT_CASAL_NUMERO_RUA
        defaultCasalShouldNotBeFound("casalNumeroRua.notEquals=" + DEFAULT_CASAL_NUMERO_RUA);

        // Get all the casalList where casalNumeroRua not equals to UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldBeFound("casalNumeroRua.notEquals=" + UPDATED_CASAL_NUMERO_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua in DEFAULT_CASAL_NUMERO_RUA or UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldBeFound("casalNumeroRua.in=" + DEFAULT_CASAL_NUMERO_RUA + "," + UPDATED_CASAL_NUMERO_RUA);

        // Get all the casalList where casalNumeroRua equals to UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldNotBeFound("casalNumeroRua.in=" + UPDATED_CASAL_NUMERO_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua is not null
        defaultCasalShouldBeFound("casalNumeroRua.specified=true");

        // Get all the casalList where casalNumeroRua is null
        defaultCasalShouldNotBeFound("casalNumeroRua.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua contains DEFAULT_CASAL_NUMERO_RUA
        defaultCasalShouldBeFound("casalNumeroRua.contains=" + DEFAULT_CASAL_NUMERO_RUA);

        // Get all the casalList where casalNumeroRua contains UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldNotBeFound("casalNumeroRua.contains=" + UPDATED_CASAL_NUMERO_RUA);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalNumeroRuaNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalNumeroRua does not contain DEFAULT_CASAL_NUMERO_RUA
        defaultCasalShouldNotBeFound("casalNumeroRua.doesNotContain=" + DEFAULT_CASAL_NUMERO_RUA);

        // Get all the casalList where casalNumeroRua does not contain UPDATED_CASAL_NUMERO_RUA
        defaultCasalShouldBeFound("casalNumeroRua.doesNotContain=" + UPDATED_CASAL_NUMERO_RUA);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro equals to DEFAULT_CASAL_BAIRRO
        defaultCasalShouldBeFound("casalBairro.equals=" + DEFAULT_CASAL_BAIRRO);

        // Get all the casalList where casalBairro equals to UPDATED_CASAL_BAIRRO
        defaultCasalShouldNotBeFound("casalBairro.equals=" + UPDATED_CASAL_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro not equals to DEFAULT_CASAL_BAIRRO
        defaultCasalShouldNotBeFound("casalBairro.notEquals=" + DEFAULT_CASAL_BAIRRO);

        // Get all the casalList where casalBairro not equals to UPDATED_CASAL_BAIRRO
        defaultCasalShouldBeFound("casalBairro.notEquals=" + UPDATED_CASAL_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalBairroIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro in DEFAULT_CASAL_BAIRRO or UPDATED_CASAL_BAIRRO
        defaultCasalShouldBeFound("casalBairro.in=" + DEFAULT_CASAL_BAIRRO + "," + UPDATED_CASAL_BAIRRO);

        // Get all the casalList where casalBairro equals to UPDATED_CASAL_BAIRRO
        defaultCasalShouldNotBeFound("casalBairro.in=" + UPDATED_CASAL_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro is not null
        defaultCasalShouldBeFound("casalBairro.specified=true");

        // Get all the casalList where casalBairro is null
        defaultCasalShouldNotBeFound("casalBairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalBairroContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro contains DEFAULT_CASAL_BAIRRO
        defaultCasalShouldBeFound("casalBairro.contains=" + DEFAULT_CASAL_BAIRRO);

        // Get all the casalList where casalBairro contains UPDATED_CASAL_BAIRRO
        defaultCasalShouldNotBeFound("casalBairro.contains=" + UPDATED_CASAL_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalBairroNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalBairro does not contain DEFAULT_CASAL_BAIRRO
        defaultCasalShouldNotBeFound("casalBairro.doesNotContain=" + DEFAULT_CASAL_BAIRRO);

        // Get all the casalList where casalBairro does not contain UPDATED_CASAL_BAIRRO
        defaultCasalShouldBeFound("casalBairro.doesNotContain=" + UPDATED_CASAL_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade equals to DEFAULT_CASAL_CIDADE
        defaultCasalShouldBeFound("casalCidade.equals=" + DEFAULT_CASAL_CIDADE);

        // Get all the casalList where casalCidade equals to UPDATED_CASAL_CIDADE
        defaultCasalShouldNotBeFound("casalCidade.equals=" + UPDATED_CASAL_CIDADE);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade not equals to DEFAULT_CASAL_CIDADE
        defaultCasalShouldNotBeFound("casalCidade.notEquals=" + DEFAULT_CASAL_CIDADE);

        // Get all the casalList where casalCidade not equals to UPDATED_CASAL_CIDADE
        defaultCasalShouldBeFound("casalCidade.notEquals=" + UPDATED_CASAL_CIDADE);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade in DEFAULT_CASAL_CIDADE or UPDATED_CASAL_CIDADE
        defaultCasalShouldBeFound("casalCidade.in=" + DEFAULT_CASAL_CIDADE + "," + UPDATED_CASAL_CIDADE);

        // Get all the casalList where casalCidade equals to UPDATED_CASAL_CIDADE
        defaultCasalShouldNotBeFound("casalCidade.in=" + UPDATED_CASAL_CIDADE);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade is not null
        defaultCasalShouldBeFound("casalCidade.specified=true");

        // Get all the casalList where casalCidade is null
        defaultCasalShouldNotBeFound("casalCidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByCasalCidadeContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade contains DEFAULT_CASAL_CIDADE
        defaultCasalShouldBeFound("casalCidade.contains=" + DEFAULT_CASAL_CIDADE);

        // Get all the casalList where casalCidade contains UPDATED_CASAL_CIDADE
        defaultCasalShouldNotBeFound("casalCidade.contains=" + UPDATED_CASAL_CIDADE);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalCidade does not contain DEFAULT_CASAL_CIDADE
        defaultCasalShouldNotBeFound("casalCidade.doesNotContain=" + DEFAULT_CASAL_CIDADE);

        // Get all the casalList where casalCidade does not contain UPDATED_CASAL_CIDADE
        defaultCasalShouldBeFound("casalCidade.doesNotContain=" + UPDATED_CASAL_CIDADE);
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalEstado equals to DEFAULT_CASAL_ESTADO
        defaultCasalShouldBeFound("casalEstado.equals=" + DEFAULT_CASAL_ESTADO);

        // Get all the casalList where casalEstado equals to UPDATED_CASAL_ESTADO
        defaultCasalShouldNotBeFound("casalEstado.equals=" + UPDATED_CASAL_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalEstado not equals to DEFAULT_CASAL_ESTADO
        defaultCasalShouldNotBeFound("casalEstado.notEquals=" + DEFAULT_CASAL_ESTADO);

        // Get all the casalList where casalEstado not equals to UPDATED_CASAL_ESTADO
        defaultCasalShouldBeFound("casalEstado.notEquals=" + UPDATED_CASAL_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalEstado in DEFAULT_CASAL_ESTADO or UPDATED_CASAL_ESTADO
        defaultCasalShouldBeFound("casalEstado.in=" + DEFAULT_CASAL_ESTADO + "," + UPDATED_CASAL_ESTADO);

        // Get all the casalList where casalEstado equals to UPDATED_CASAL_ESTADO
        defaultCasalShouldNotBeFound("casalEstado.in=" + UPDATED_CASAL_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCasalsByCasalEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where casalEstado is not null
        defaultCasalShouldBeFound("casalEstado.specified=true");

        // Get all the casalList where casalEstado is null
        defaultCasalShouldNotBeFound("casalEstado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao equals to DEFAULT_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.equals=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao equals to UPDATED_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.equals=" + UPDATED_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao not equals to DEFAULT_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.notEquals=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao not equals to UPDATED_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.notEquals=" + UPDATED_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao in DEFAULT_DATA_UNIAO or UPDATED_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.in=" + DEFAULT_DATA_UNIAO + "," + UPDATED_DATA_UNIAO);

        // Get all the casalList where dataUniao equals to UPDATED_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.in=" + UPDATED_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao is not null
        defaultCasalShouldBeFound("dataUniao.specified=true");

        // Get all the casalList where dataUniao is null
        defaultCasalShouldNotBeFound("dataUniao.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao is greater than or equal to DEFAULT_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.greaterThanOrEqual=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao is greater than or equal to UPDATED_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.greaterThanOrEqual=" + UPDATED_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao is less than or equal to DEFAULT_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.lessThanOrEqual=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao is less than or equal to SMALLER_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.lessThanOrEqual=" + SMALLER_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsLessThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao is less than DEFAULT_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.lessThan=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao is less than UPDATED_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.lessThan=" + UPDATED_DATA_UNIAO);
    }

    @Test
    @Transactional
    public void getAllCasalsByDataUniaoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where dataUniao is greater than DEFAULT_DATA_UNIAO
        defaultCasalShouldNotBeFound("dataUniao.greaterThan=" + DEFAULT_DATA_UNIAO);

        // Get all the casalList where dataUniao is greater than SMALLER_DATA_UNIAO
        defaultCasalShouldBeFound("dataUniao.greaterThan=" + SMALLER_DATA_UNIAO);
    }


    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha equals to DEFAULT_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.equals=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha equals to UPDATED_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.equals=" + UPDATED_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha not equals to DEFAULT_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.notEquals=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha not equals to UPDATED_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.notEquals=" + UPDATED_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha in DEFAULT_NUMERO_FICHA or UPDATED_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.in=" + DEFAULT_NUMERO_FICHA + "," + UPDATED_NUMERO_FICHA);

        // Get all the casalList where numeroFicha equals to UPDATED_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.in=" + UPDATED_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha is not null
        defaultCasalShouldBeFound("numeroFicha.specified=true");

        // Get all the casalList where numeroFicha is null
        defaultCasalShouldNotBeFound("numeroFicha.specified=false");
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha is greater than or equal to DEFAULT_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.greaterThanOrEqual=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha is greater than or equal to UPDATED_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.greaterThanOrEqual=" + UPDATED_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha is less than or equal to DEFAULT_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.lessThanOrEqual=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha is less than or equal to SMALLER_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.lessThanOrEqual=" + SMALLER_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsLessThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha is less than DEFAULT_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.lessThan=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha is less than UPDATED_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.lessThan=" + UPDATED_NUMERO_FICHA);
    }

    @Test
    @Transactional
    public void getAllCasalsByNumeroFichaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where numeroFicha is greater than DEFAULT_NUMERO_FICHA
        defaultCasalShouldNotBeFound("numeroFicha.greaterThan=" + DEFAULT_NUMERO_FICHA);

        // Get all the casalList where numeroFicha is greater than SMALLER_NUMERO_FICHA
        defaultCasalShouldBeFound("numeroFicha.greaterThan=" + SMALLER_NUMERO_FICHA);
    }


    @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal equals to DEFAULT_INFORMACOES_CASAL
        defaultCasalShouldBeFound("informacoesCasal.equals=" + DEFAULT_INFORMACOES_CASAL);

        // Get all the casalList where informacoesCasal equals to UPDATED_INFORMACOES_CASAL
        defaultCasalShouldNotBeFound("informacoesCasal.equals=" + UPDATED_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal not equals to DEFAULT_INFORMACOES_CASAL
        defaultCasalShouldNotBeFound("informacoesCasal.notEquals=" + DEFAULT_INFORMACOES_CASAL);

        // Get all the casalList where informacoesCasal not equals to UPDATED_INFORMACOES_CASAL
        defaultCasalShouldBeFound("informacoesCasal.notEquals=" + UPDATED_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalIsInShouldWork() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal in DEFAULT_INFORMACOES_CASAL or UPDATED_INFORMACOES_CASAL
        defaultCasalShouldBeFound("informacoesCasal.in=" + DEFAULT_INFORMACOES_CASAL + "," + UPDATED_INFORMACOES_CASAL);

        // Get all the casalList where informacoesCasal equals to UPDATED_INFORMACOES_CASAL
        defaultCasalShouldNotBeFound("informacoesCasal.in=" + UPDATED_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalIsNullOrNotNull() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal is not null
        defaultCasalShouldBeFound("informacoesCasal.specified=true");

        // Get all the casalList where informacoesCasal is null
        defaultCasalShouldNotBeFound("informacoesCasal.specified=false");
    }
                @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal contains DEFAULT_INFORMACOES_CASAL
        defaultCasalShouldBeFound("informacoesCasal.contains=" + DEFAULT_INFORMACOES_CASAL);

        // Get all the casalList where informacoesCasal contains UPDATED_INFORMACOES_CASAL
        defaultCasalShouldNotBeFound("informacoesCasal.contains=" + UPDATED_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void getAllCasalsByInformacoesCasalNotContainsSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        // Get all the casalList where informacoesCasal does not contain DEFAULT_INFORMACOES_CASAL
        defaultCasalShouldNotBeFound("informacoesCasal.doesNotContain=" + DEFAULT_INFORMACOES_CASAL);

        // Get all the casalList where informacoesCasal does not contain UPDATED_INFORMACOES_CASAL
        defaultCasalShouldBeFound("informacoesCasal.doesNotContain=" + UPDATED_INFORMACOES_CASAL);
    }


    @Test
    @Transactional
    public void getAllCasalsByFilhoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);
        Filho filho = FilhoResourceIT.createEntity(em);
        em.persist(filho);
        em.flush();
        casal.addFilho(filho);
        casalRepository.saveAndFlush(casal);
        Long filhoId = filho.getId();

        // Get all the casalList where filho equals to filhoId
        defaultCasalShouldBeFound("filhoId.equals=" + filhoId);

        // Get all the casalList where filho equals to filhoId + 1
        defaultCasalShouldNotBeFound("filhoId.equals=" + (filhoId + 1));
    }


    @Test
    @Transactional
    public void getAllCasalsByApadrinhadoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);
        Casal apadrinhado = CasalResourceIT.createEntity(em);
        em.persist(apadrinhado);
        em.flush();
        casal.addApadrinhado(apadrinhado);
        casalRepository.saveAndFlush(casal);
        Long apadrinhadoId = apadrinhado.getId();

        // Get all the casalList where apadrinhado equals to apadrinhadoId
        defaultCasalShouldBeFound("apadrinhadoId.equals=" + apadrinhadoId);

        // Get all the casalList where apadrinhado equals to apadrinhadoId + 1
        defaultCasalShouldNotBeFound("apadrinhadoId.equals=" + (apadrinhadoId + 1));
    }


    @Test
    @Transactional
    public void getAllCasalsByLareiraIsEqualToSomething() throws Exception {
        // Get already existing entity
        Lareira lareira = casal.getLareira();
        casalRepository.saveAndFlush(casal);
        Long lareiraId = lareira.getId();

        // Get all the casalList where lareira equals to lareiraId
        defaultCasalShouldBeFound("lareiraId.equals=" + lareiraId);

        // Get all the casalList where lareira equals to lareiraId + 1
        defaultCasalShouldNotBeFound("lareiraId.equals=" + (lareiraId + 1));
    }


    @Test
    @Transactional
    public void getAllCasalsByTipoUniaoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);
        TipoUniao tipoUniao = TipoUniaoResourceIT.createEntity(em);
        em.persist(tipoUniao);
        em.flush();
        casal.setTipoUniao(tipoUniao);
        casalRepository.saveAndFlush(casal);
        Long tipoUniaoId = tipoUniao.getId();

        // Get all the casalList where tipoUniao equals to tipoUniaoId
        defaultCasalShouldBeFound("tipoUniaoId.equals=" + tipoUniaoId);

        // Get all the casalList where tipoUniao equals to tipoUniaoId + 1
        defaultCasalShouldNotBeFound("tipoUniaoId.equals=" + (tipoUniaoId + 1));
    }


    @Test
    @Transactional
    public void getAllCasalsByCasalPadrinhoIsEqualToSomething() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);
        Casal casalPadrinho = CasalResourceIT.createEntity(em);
        em.persist(casalPadrinho);
        em.flush();
        casal.setCasalPadrinho(casalPadrinho);
        casalRepository.saveAndFlush(casal);
        Long casalPadrinhoId = casalPadrinho.getId();

        // Get all the casalList where casalPadrinho equals to casalPadrinhoId
        defaultCasalShouldBeFound("casalPadrinhoId.equals=" + casalPadrinhoId);

        // Get all the casalList where casalPadrinho equals to casalPadrinhoId + 1
        defaultCasalShouldNotBeFound("casalPadrinhoId.equals=" + (casalPadrinhoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCasalShouldBeFound(String filter) throws Exception {
        restCasalMockMvc.perform(get("/api/casals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(casal.getId().intValue())))
            .andExpect(jsonPath("$.[*].maridoNome").value(hasItem(DEFAULT_MARIDO_NOME)))
            .andExpect(jsonPath("$.[*].maridoSobrenome").value(hasItem(DEFAULT_MARIDO_SOBRENOME)))
            .andExpect(jsonPath("$.[*].maridoDataNascimento").value(hasItem(DEFAULT_MARIDO_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].maridoProfissao").value(hasItem(DEFAULT_MARIDO_PROFISSAO)))
            .andExpect(jsonPath("$.[*].maridoTelCelular").value(hasItem(DEFAULT_MARIDO_TEL_CELULAR)))
            .andExpect(jsonPath("$.[*].maridoEmail").value(hasItem(DEFAULT_MARIDO_EMAIL)))
            .andExpect(jsonPath("$.[*].maridoProblemaSaude").value(hasItem(DEFAULT_MARIDO_PROBLEMA_SAUDE)))
            .andExpect(jsonPath("$.[*].esposaNome").value(hasItem(DEFAULT_ESPOSA_NOME)))
            .andExpect(jsonPath("$.[*].esposaSobrenome").value(hasItem(DEFAULT_ESPOSA_SOBRENOME)))
            .andExpect(jsonPath("$.[*].esposaDataNascimento").value(hasItem(DEFAULT_ESPOSA_DATA_NASCIMENTO.toString())))
            .andExpect(jsonPath("$.[*].esposaProfissao").value(hasItem(DEFAULT_ESPOSA_PROFISSAO)))
            .andExpect(jsonPath("$.[*].esposaTelCelular").value(hasItem(DEFAULT_ESPOSA_TEL_CELULAR)))
            .andExpect(jsonPath("$.[*].esposaEmail").value(hasItem(DEFAULT_ESPOSA_EMAIL)))
            .andExpect(jsonPath("$.[*].esposaProblemaSaude").value(hasItem(DEFAULT_ESPOSA_PROBLEMA_SAUDE)))
            .andExpect(jsonPath("$.[*].casalFoneFixo").value(hasItem(DEFAULT_CASAL_FONE_FIXO)))
            .andExpect(jsonPath("$.[*].casalFoneContato").value(hasItem(DEFAULT_CASAL_FONE_CONTATO)))
            .andExpect(jsonPath("$.[*].casalCep").value(hasItem(DEFAULT_CASAL_CEP)))
            .andExpect(jsonPath("$.[*].casalNomeRua").value(hasItem(DEFAULT_CASAL_NOME_RUA)))
            .andExpect(jsonPath("$.[*].casalNumeroRua").value(hasItem(DEFAULT_CASAL_NUMERO_RUA)))
            .andExpect(jsonPath("$.[*].casalBairro").value(hasItem(DEFAULT_CASAL_BAIRRO)))
            .andExpect(jsonPath("$.[*].casalCidade").value(hasItem(DEFAULT_CASAL_CIDADE)))
            .andExpect(jsonPath("$.[*].casalEstado").value(hasItem(DEFAULT_CASAL_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].fotoCasalContentType").value(hasItem(DEFAULT_FOTO_CASAL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fotoCasal").value(hasItem(Base64Utils.encodeToString(DEFAULT_FOTO_CASAL))))
            .andExpect(jsonPath("$.[*].dataUniao").value(hasItem(DEFAULT_DATA_UNIAO.toString())))
            .andExpect(jsonPath("$.[*].numeroFicha").value(hasItem(DEFAULT_NUMERO_FICHA)))
            .andExpect(jsonPath("$.[*].informacoesCasal").value(hasItem(DEFAULT_INFORMACOES_CASAL)));

        // Check, that the count call also returns 1
        restCasalMockMvc.perform(get("/api/casals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCasalShouldNotBeFound(String filter) throws Exception {
        restCasalMockMvc.perform(get("/api/casals?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCasalMockMvc.perform(get("/api/casals/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCasal() throws Exception {
        // Get the casal
        restCasalMockMvc.perform(get("/api/casals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCasal() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        int databaseSizeBeforeUpdate = casalRepository.findAll().size();

        // Update the casal
        Casal updatedCasal = casalRepository.findById(casal.getId()).get();
        // Disconnect from session so that the updates on updatedCasal are not directly saved in db
        em.detach(updatedCasal);
        updatedCasal
            .maridoNome(UPDATED_MARIDO_NOME)
            .maridoSobrenome(UPDATED_MARIDO_SOBRENOME)
            .maridoDataNascimento(UPDATED_MARIDO_DATA_NASCIMENTO)
            .maridoProfissao(UPDATED_MARIDO_PROFISSAO)
            .maridoTelCelular(UPDATED_MARIDO_TEL_CELULAR)
            .maridoEmail(UPDATED_MARIDO_EMAIL)
            .maridoProblemaSaude(UPDATED_MARIDO_PROBLEMA_SAUDE)
            .esposaNome(UPDATED_ESPOSA_NOME)
            .esposaSobrenome(UPDATED_ESPOSA_SOBRENOME)
            .esposaDataNascimento(UPDATED_ESPOSA_DATA_NASCIMENTO)
            .esposaProfissao(UPDATED_ESPOSA_PROFISSAO)
            .esposaTelCelular(UPDATED_ESPOSA_TEL_CELULAR)
            .esposaEmail(UPDATED_ESPOSA_EMAIL)
            .esposaProblemaSaude(UPDATED_ESPOSA_PROBLEMA_SAUDE)
            .casalFoneFixo(UPDATED_CASAL_FONE_FIXO)
            .casalFoneContato(UPDATED_CASAL_FONE_CONTATO)
            .casalCep(UPDATED_CASAL_CEP)
            .casalNomeRua(UPDATED_CASAL_NOME_RUA)
            .casalNumeroRua(UPDATED_CASAL_NUMERO_RUA)
            .casalBairro(UPDATED_CASAL_BAIRRO)
            .casalCidade(UPDATED_CASAL_CIDADE)
            .casalEstado(UPDATED_CASAL_ESTADO)
            .fotoCasal(UPDATED_FOTO_CASAL)
            .fotoCasalContentType(UPDATED_FOTO_CASAL_CONTENT_TYPE)
            .dataUniao(UPDATED_DATA_UNIAO)
            .numeroFicha(UPDATED_NUMERO_FICHA)
            .informacoesCasal(UPDATED_INFORMACOES_CASAL);
        CasalDTO casalDTO = casalMapper.toDto(updatedCasal);

        restCasalMockMvc.perform(put("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isOk());

        // Validate the Casal in the database
        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeUpdate);
        Casal testCasal = casalList.get(casalList.size() - 1);
        assertThat(testCasal.getMaridoNome()).isEqualTo(UPDATED_MARIDO_NOME);
        assertThat(testCasal.getMaridoSobrenome()).isEqualTo(UPDATED_MARIDO_SOBRENOME);
        assertThat(testCasal.getMaridoDataNascimento()).isEqualTo(UPDATED_MARIDO_DATA_NASCIMENTO);
        assertThat(testCasal.getMaridoProfissao()).isEqualTo(UPDATED_MARIDO_PROFISSAO);
        assertThat(testCasal.getMaridoTelCelular()).isEqualTo(UPDATED_MARIDO_TEL_CELULAR);
        assertThat(testCasal.getMaridoEmail()).isEqualTo(UPDATED_MARIDO_EMAIL);
        assertThat(testCasal.getMaridoProblemaSaude()).isEqualTo(UPDATED_MARIDO_PROBLEMA_SAUDE);
        assertThat(testCasal.getEsposaNome()).isEqualTo(UPDATED_ESPOSA_NOME);
        assertThat(testCasal.getEsposaSobrenome()).isEqualTo(UPDATED_ESPOSA_SOBRENOME);
        assertThat(testCasal.getEsposaDataNascimento()).isEqualTo(UPDATED_ESPOSA_DATA_NASCIMENTO);
        assertThat(testCasal.getEsposaProfissao()).isEqualTo(UPDATED_ESPOSA_PROFISSAO);
        assertThat(testCasal.getEsposaTelCelular()).isEqualTo(UPDATED_ESPOSA_TEL_CELULAR);
        assertThat(testCasal.getEsposaEmail()).isEqualTo(UPDATED_ESPOSA_EMAIL);
        assertThat(testCasal.getEsposaProblemaSaude()).isEqualTo(UPDATED_ESPOSA_PROBLEMA_SAUDE);
        assertThat(testCasal.getCasalFoneFixo()).isEqualTo(UPDATED_CASAL_FONE_FIXO);
        assertThat(testCasal.getCasalFoneContato()).isEqualTo(UPDATED_CASAL_FONE_CONTATO);
        assertThat(testCasal.getCasalCep()).isEqualTo(UPDATED_CASAL_CEP);
        assertThat(testCasal.getCasalNomeRua()).isEqualTo(UPDATED_CASAL_NOME_RUA);
        assertThat(testCasal.getCasalNumeroRua()).isEqualTo(UPDATED_CASAL_NUMERO_RUA);
        assertThat(testCasal.getCasalBairro()).isEqualTo(UPDATED_CASAL_BAIRRO);
        assertThat(testCasal.getCasalCidade()).isEqualTo(UPDATED_CASAL_CIDADE);
        assertThat(testCasal.getCasalEstado()).isEqualTo(UPDATED_CASAL_ESTADO);
        assertThat(testCasal.getFotoCasal()).isEqualTo(UPDATED_FOTO_CASAL);
        assertThat(testCasal.getFotoCasalContentType()).isEqualTo(UPDATED_FOTO_CASAL_CONTENT_TYPE);
        assertThat(testCasal.getDataUniao()).isEqualTo(UPDATED_DATA_UNIAO);
        assertThat(testCasal.getNumeroFicha()).isEqualTo(UPDATED_NUMERO_FICHA);
        assertThat(testCasal.getInformacoesCasal()).isEqualTo(UPDATED_INFORMACOES_CASAL);
    }

    @Test
    @Transactional
    public void updateNonExistingCasal() throws Exception {
        int databaseSizeBeforeUpdate = casalRepository.findAll().size();

        // Create the Casal
        CasalDTO casalDTO = casalMapper.toDto(casal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCasalMockMvc.perform(put("/api/casals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(casalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Casal in the database
        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCasal() throws Exception {
        // Initialize the database
        casalRepository.saveAndFlush(casal);

        int databaseSizeBeforeDelete = casalRepository.findAll().size();

        // Delete the casal
        restCasalMockMvc.perform(delete("/api/casals/{id}", casal.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Casal> casalList = casalRepository.findAll();
        assertThat(casalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
