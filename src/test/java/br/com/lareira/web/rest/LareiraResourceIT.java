package br.com.lareira.web.rest;

import br.com.lareira.LareiraAppHomoApp;
import br.com.lareira.domain.Lareira;
import br.com.lareira.domain.Casal;
import br.com.lareira.repository.LareiraRepository;
import br.com.lareira.service.LareiraService;
import br.com.lareira.service.dto.LareiraCriteria;
import br.com.lareira.service.LareiraQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LareiraResource} REST controller.
 */
@SpringBootTest(classes = LareiraAppHomoApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LareiraResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_BAIRRO = "AAAAAAAAAA";
    private static final String UPDATED_BAIRRO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    @Autowired
    private LareiraRepository lareiraRepository;

    @Autowired
    private LareiraService lareiraService;

    @Autowired
    private LareiraQueryService lareiraQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLareiraMockMvc;

    private Lareira lareira;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lareira createEntity(EntityManager em) {
        Lareira lareira = new Lareira()
            .nome(DEFAULT_NOME)
            .endereco(DEFAULT_ENDERECO)
            .bairro(DEFAULT_BAIRRO)
            .cep(DEFAULT_CEP)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .telefone(DEFAULT_TELEFONE);
        return lareira;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lareira createUpdatedEntity(EntityManager em) {
        Lareira lareira = new Lareira()
            .nome(UPDATED_NOME)
            .endereco(UPDATED_ENDERECO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE);
        return lareira;
    }

    @BeforeEach
    public void initTest() {
        lareira = createEntity(em);
    }

    @Test
    @Transactional
    public void createLareira() throws Exception {
        int databaseSizeBeforeCreate = lareiraRepository.findAll().size();

        // Create the Lareira
        restLareiraMockMvc.perform(post("/api/lareiras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lareira)))
            .andExpect(status().isCreated());

        // Validate the Lareira in the database
        List<Lareira> lareiraList = lareiraRepository.findAll();
        assertThat(lareiraList).hasSize(databaseSizeBeforeCreate + 1);
        Lareira testLareira = lareiraList.get(lareiraList.size() - 1);
        assertThat(testLareira.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testLareira.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
        assertThat(testLareira.getBairro()).isEqualTo(DEFAULT_BAIRRO);
        assertThat(testLareira.getCep()).isEqualTo(DEFAULT_CEP);
        assertThat(testLareira.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testLareira.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testLareira.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
    }

    @Test
    @Transactional
    public void createLareiraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lareiraRepository.findAll().size();

        // Create the Lareira with an existing ID
        lareira.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLareiraMockMvc.perform(post("/api/lareiras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lareira)))
            .andExpect(status().isBadRequest());

        // Validate the Lareira in the database
        List<Lareira> lareiraList = lareiraRepository.findAll();
        assertThat(lareiraList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLareiras() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList
        restLareiraMockMvc.perform(get("/api/lareiras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lareira.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)));
    }
    
    @Test
    @Transactional
    public void getLareira() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get the lareira
        restLareiraMockMvc.perform(get("/api/lareiras/{id}", lareira.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lareira.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO))
            .andExpect(jsonPath("$.bairro").value(DEFAULT_BAIRRO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE));
    }


    @Test
    @Transactional
    public void getLareirasByIdFiltering() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        Long id = lareira.getId();

        defaultLareiraShouldBeFound("id.equals=" + id);
        defaultLareiraShouldNotBeFound("id.notEquals=" + id);

        defaultLareiraShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLareiraShouldNotBeFound("id.greaterThan=" + id);

        defaultLareiraShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLareiraShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLareirasByNomeIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome equals to DEFAULT_NOME
        defaultLareiraShouldBeFound("nome.equals=" + DEFAULT_NOME);

        // Get all the lareiraList where nome equals to UPDATED_NOME
        defaultLareiraShouldNotBeFound("nome.equals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllLareirasByNomeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome not equals to DEFAULT_NOME
        defaultLareiraShouldNotBeFound("nome.notEquals=" + DEFAULT_NOME);

        // Get all the lareiraList where nome not equals to UPDATED_NOME
        defaultLareiraShouldBeFound("nome.notEquals=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllLareirasByNomeIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome in DEFAULT_NOME or UPDATED_NOME
        defaultLareiraShouldBeFound("nome.in=" + DEFAULT_NOME + "," + UPDATED_NOME);

        // Get all the lareiraList where nome equals to UPDATED_NOME
        defaultLareiraShouldNotBeFound("nome.in=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllLareirasByNomeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome is not null
        defaultLareiraShouldBeFound("nome.specified=true");

        // Get all the lareiraList where nome is null
        defaultLareiraShouldNotBeFound("nome.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByNomeContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome contains DEFAULT_NOME
        defaultLareiraShouldBeFound("nome.contains=" + DEFAULT_NOME);

        // Get all the lareiraList where nome contains UPDATED_NOME
        defaultLareiraShouldNotBeFound("nome.contains=" + UPDATED_NOME);
    }

    @Test
    @Transactional
    public void getAllLareirasByNomeNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where nome does not contain DEFAULT_NOME
        defaultLareiraShouldNotBeFound("nome.doesNotContain=" + DEFAULT_NOME);

        // Get all the lareiraList where nome does not contain UPDATED_NOME
        defaultLareiraShouldBeFound("nome.doesNotContain=" + UPDATED_NOME);
    }


    @Test
    @Transactional
    public void getAllLareirasByEnderecoIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco equals to DEFAULT_ENDERECO
        defaultLareiraShouldBeFound("endereco.equals=" + DEFAULT_ENDERECO);

        // Get all the lareiraList where endereco equals to UPDATED_ENDERECO
        defaultLareiraShouldNotBeFound("endereco.equals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEnderecoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco not equals to DEFAULT_ENDERECO
        defaultLareiraShouldNotBeFound("endereco.notEquals=" + DEFAULT_ENDERECO);

        // Get all the lareiraList where endereco not equals to UPDATED_ENDERECO
        defaultLareiraShouldBeFound("endereco.notEquals=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEnderecoIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco in DEFAULT_ENDERECO or UPDATED_ENDERECO
        defaultLareiraShouldBeFound("endereco.in=" + DEFAULT_ENDERECO + "," + UPDATED_ENDERECO);

        // Get all the lareiraList where endereco equals to UPDATED_ENDERECO
        defaultLareiraShouldNotBeFound("endereco.in=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEnderecoIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco is not null
        defaultLareiraShouldBeFound("endereco.specified=true");

        // Get all the lareiraList where endereco is null
        defaultLareiraShouldNotBeFound("endereco.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByEnderecoContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco contains DEFAULT_ENDERECO
        defaultLareiraShouldBeFound("endereco.contains=" + DEFAULT_ENDERECO);

        // Get all the lareiraList where endereco contains UPDATED_ENDERECO
        defaultLareiraShouldNotBeFound("endereco.contains=" + UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEnderecoNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where endereco does not contain DEFAULT_ENDERECO
        defaultLareiraShouldNotBeFound("endereco.doesNotContain=" + DEFAULT_ENDERECO);

        // Get all the lareiraList where endereco does not contain UPDATED_ENDERECO
        defaultLareiraShouldBeFound("endereco.doesNotContain=" + UPDATED_ENDERECO);
    }


    @Test
    @Transactional
    public void getAllLareirasByBairroIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro equals to DEFAULT_BAIRRO
        defaultLareiraShouldBeFound("bairro.equals=" + DEFAULT_BAIRRO);

        // Get all the lareiraList where bairro equals to UPDATED_BAIRRO
        defaultLareiraShouldNotBeFound("bairro.equals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllLareirasByBairroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro not equals to DEFAULT_BAIRRO
        defaultLareiraShouldNotBeFound("bairro.notEquals=" + DEFAULT_BAIRRO);

        // Get all the lareiraList where bairro not equals to UPDATED_BAIRRO
        defaultLareiraShouldBeFound("bairro.notEquals=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllLareirasByBairroIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro in DEFAULT_BAIRRO or UPDATED_BAIRRO
        defaultLareiraShouldBeFound("bairro.in=" + DEFAULT_BAIRRO + "," + UPDATED_BAIRRO);

        // Get all the lareiraList where bairro equals to UPDATED_BAIRRO
        defaultLareiraShouldNotBeFound("bairro.in=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllLareirasByBairroIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro is not null
        defaultLareiraShouldBeFound("bairro.specified=true");

        // Get all the lareiraList where bairro is null
        defaultLareiraShouldNotBeFound("bairro.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByBairroContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro contains DEFAULT_BAIRRO
        defaultLareiraShouldBeFound("bairro.contains=" + DEFAULT_BAIRRO);

        // Get all the lareiraList where bairro contains UPDATED_BAIRRO
        defaultLareiraShouldNotBeFound("bairro.contains=" + UPDATED_BAIRRO);
    }

    @Test
    @Transactional
    public void getAllLareirasByBairroNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where bairro does not contain DEFAULT_BAIRRO
        defaultLareiraShouldNotBeFound("bairro.doesNotContain=" + DEFAULT_BAIRRO);

        // Get all the lareiraList where bairro does not contain UPDATED_BAIRRO
        defaultLareiraShouldBeFound("bairro.doesNotContain=" + UPDATED_BAIRRO);
    }


    @Test
    @Transactional
    public void getAllLareirasByCepIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep equals to DEFAULT_CEP
        defaultLareiraShouldBeFound("cep.equals=" + DEFAULT_CEP);

        // Get all the lareiraList where cep equals to UPDATED_CEP
        defaultLareiraShouldNotBeFound("cep.equals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllLareirasByCepIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep not equals to DEFAULT_CEP
        defaultLareiraShouldNotBeFound("cep.notEquals=" + DEFAULT_CEP);

        // Get all the lareiraList where cep not equals to UPDATED_CEP
        defaultLareiraShouldBeFound("cep.notEquals=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllLareirasByCepIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep in DEFAULT_CEP or UPDATED_CEP
        defaultLareiraShouldBeFound("cep.in=" + DEFAULT_CEP + "," + UPDATED_CEP);

        // Get all the lareiraList where cep equals to UPDATED_CEP
        defaultLareiraShouldNotBeFound("cep.in=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllLareirasByCepIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep is not null
        defaultLareiraShouldBeFound("cep.specified=true");

        // Get all the lareiraList where cep is null
        defaultLareiraShouldNotBeFound("cep.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByCepContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep contains DEFAULT_CEP
        defaultLareiraShouldBeFound("cep.contains=" + DEFAULT_CEP);

        // Get all the lareiraList where cep contains UPDATED_CEP
        defaultLareiraShouldNotBeFound("cep.contains=" + UPDATED_CEP);
    }

    @Test
    @Transactional
    public void getAllLareirasByCepNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cep does not contain DEFAULT_CEP
        defaultLareiraShouldNotBeFound("cep.doesNotContain=" + DEFAULT_CEP);

        // Get all the lareiraList where cep does not contain UPDATED_CEP
        defaultLareiraShouldBeFound("cep.doesNotContain=" + UPDATED_CEP);
    }


    @Test
    @Transactional
    public void getAllLareirasByCidadeIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade equals to DEFAULT_CIDADE
        defaultLareiraShouldBeFound("cidade.equals=" + DEFAULT_CIDADE);

        // Get all the lareiraList where cidade equals to UPDATED_CIDADE
        defaultLareiraShouldNotBeFound("cidade.equals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllLareirasByCidadeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade not equals to DEFAULT_CIDADE
        defaultLareiraShouldNotBeFound("cidade.notEquals=" + DEFAULT_CIDADE);

        // Get all the lareiraList where cidade not equals to UPDATED_CIDADE
        defaultLareiraShouldBeFound("cidade.notEquals=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllLareirasByCidadeIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade in DEFAULT_CIDADE or UPDATED_CIDADE
        defaultLareiraShouldBeFound("cidade.in=" + DEFAULT_CIDADE + "," + UPDATED_CIDADE);

        // Get all the lareiraList where cidade equals to UPDATED_CIDADE
        defaultLareiraShouldNotBeFound("cidade.in=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllLareirasByCidadeIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade is not null
        defaultLareiraShouldBeFound("cidade.specified=true");

        // Get all the lareiraList where cidade is null
        defaultLareiraShouldNotBeFound("cidade.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByCidadeContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade contains DEFAULT_CIDADE
        defaultLareiraShouldBeFound("cidade.contains=" + DEFAULT_CIDADE);

        // Get all the lareiraList where cidade contains UPDATED_CIDADE
        defaultLareiraShouldNotBeFound("cidade.contains=" + UPDATED_CIDADE);
    }

    @Test
    @Transactional
    public void getAllLareirasByCidadeNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where cidade does not contain DEFAULT_CIDADE
        defaultLareiraShouldNotBeFound("cidade.doesNotContain=" + DEFAULT_CIDADE);

        // Get all the lareiraList where cidade does not contain UPDATED_CIDADE
        defaultLareiraShouldBeFound("cidade.doesNotContain=" + UPDATED_CIDADE);
    }


    @Test
    @Transactional
    public void getAllLareirasByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado equals to DEFAULT_ESTADO
        defaultLareiraShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the lareiraList where estado equals to UPDATED_ESTADO
        defaultLareiraShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado not equals to DEFAULT_ESTADO
        defaultLareiraShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the lareiraList where estado not equals to UPDATED_ESTADO
        defaultLareiraShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultLareiraShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the lareiraList where estado equals to UPDATED_ESTADO
        defaultLareiraShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado is not null
        defaultLareiraShouldBeFound("estado.specified=true");

        // Get all the lareiraList where estado is null
        defaultLareiraShouldNotBeFound("estado.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByEstadoContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado contains DEFAULT_ESTADO
        defaultLareiraShouldBeFound("estado.contains=" + DEFAULT_ESTADO);

        // Get all the lareiraList where estado contains UPDATED_ESTADO
        defaultLareiraShouldNotBeFound("estado.contains=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllLareirasByEstadoNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where estado does not contain DEFAULT_ESTADO
        defaultLareiraShouldNotBeFound("estado.doesNotContain=" + DEFAULT_ESTADO);

        // Get all the lareiraList where estado does not contain UPDATED_ESTADO
        defaultLareiraShouldBeFound("estado.doesNotContain=" + UPDATED_ESTADO);
    }


    @Test
    @Transactional
    public void getAllLareirasByTelefoneIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone equals to DEFAULT_TELEFONE
        defaultLareiraShouldBeFound("telefone.equals=" + DEFAULT_TELEFONE);

        // Get all the lareiraList where telefone equals to UPDATED_TELEFONE
        defaultLareiraShouldNotBeFound("telefone.equals=" + UPDATED_TELEFONE);
    }

    @Test
    @Transactional
    public void getAllLareirasByTelefoneIsNotEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone not equals to DEFAULT_TELEFONE
        defaultLareiraShouldNotBeFound("telefone.notEquals=" + DEFAULT_TELEFONE);

        // Get all the lareiraList where telefone not equals to UPDATED_TELEFONE
        defaultLareiraShouldBeFound("telefone.notEquals=" + UPDATED_TELEFONE);
    }

    @Test
    @Transactional
    public void getAllLareirasByTelefoneIsInShouldWork() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone in DEFAULT_TELEFONE or UPDATED_TELEFONE
        defaultLareiraShouldBeFound("telefone.in=" + DEFAULT_TELEFONE + "," + UPDATED_TELEFONE);

        // Get all the lareiraList where telefone equals to UPDATED_TELEFONE
        defaultLareiraShouldNotBeFound("telefone.in=" + UPDATED_TELEFONE);
    }

    @Test
    @Transactional
    public void getAllLareirasByTelefoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone is not null
        defaultLareiraShouldBeFound("telefone.specified=true");

        // Get all the lareiraList where telefone is null
        defaultLareiraShouldNotBeFound("telefone.specified=false");
    }
                @Test
    @Transactional
    public void getAllLareirasByTelefoneContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone contains DEFAULT_TELEFONE
        defaultLareiraShouldBeFound("telefone.contains=" + DEFAULT_TELEFONE);

        // Get all the lareiraList where telefone contains UPDATED_TELEFONE
        defaultLareiraShouldNotBeFound("telefone.contains=" + UPDATED_TELEFONE);
    }

    @Test
    @Transactional
    public void getAllLareirasByTelefoneNotContainsSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);

        // Get all the lareiraList where telefone does not contain DEFAULT_TELEFONE
        defaultLareiraShouldNotBeFound("telefone.doesNotContain=" + DEFAULT_TELEFONE);

        // Get all the lareiraList where telefone does not contain UPDATED_TELEFONE
        defaultLareiraShouldBeFound("telefone.doesNotContain=" + UPDATED_TELEFONE);
    }


    @Test
    @Transactional
    public void getAllLareirasByCasalIsEqualToSomething() throws Exception {
        // Initialize the database
        lareiraRepository.saveAndFlush(lareira);
        Casal casal = CasalResourceIT.createEntity(em);
        em.persist(casal);
        em.flush();
        lareira.addCasal(casal);
        lareiraRepository.saveAndFlush(lareira);
        Long casalId = casal.getId();

        // Get all the lareiraList where casal equals to casalId
        defaultLareiraShouldBeFound("casalId.equals=" + casalId);

        // Get all the lareiraList where casal equals to casalId + 1
        defaultLareiraShouldNotBeFound("casalId.equals=" + (casalId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLareiraShouldBeFound(String filter) throws Exception {
        restLareiraMockMvc.perform(get("/api/lareiras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lareira.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].bairro").value(hasItem(DEFAULT_BAIRRO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)));

        // Check, that the count call also returns 1
        restLareiraMockMvc.perform(get("/api/lareiras/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLareiraShouldNotBeFound(String filter) throws Exception {
        restLareiraMockMvc.perform(get("/api/lareiras?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLareiraMockMvc.perform(get("/api/lareiras/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLareira() throws Exception {
        // Get the lareira
        restLareiraMockMvc.perform(get("/api/lareiras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLareira() throws Exception {
        // Initialize the database
        lareiraService.save(lareira);

        int databaseSizeBeforeUpdate = lareiraRepository.findAll().size();

        // Update the lareira
        Lareira updatedLareira = lareiraRepository.findById(lareira.getId()).get();
        // Disconnect from session so that the updates on updatedLareira are not directly saved in db
        em.detach(updatedLareira);
        updatedLareira
            .nome(UPDATED_NOME)
            .endereco(UPDATED_ENDERECO)
            .bairro(UPDATED_BAIRRO)
            .cep(UPDATED_CEP)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE);

        restLareiraMockMvc.perform(put("/api/lareiras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLareira)))
            .andExpect(status().isOk());

        // Validate the Lareira in the database
        List<Lareira> lareiraList = lareiraRepository.findAll();
        assertThat(lareiraList).hasSize(databaseSizeBeforeUpdate);
        Lareira testLareira = lareiraList.get(lareiraList.size() - 1);
        assertThat(testLareira.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testLareira.getEndereco()).isEqualTo(UPDATED_ENDERECO);
        assertThat(testLareira.getBairro()).isEqualTo(UPDATED_BAIRRO);
        assertThat(testLareira.getCep()).isEqualTo(UPDATED_CEP);
        assertThat(testLareira.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testLareira.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testLareira.getTelefone()).isEqualTo(UPDATED_TELEFONE);
    }

    @Test
    @Transactional
    public void updateNonExistingLareira() throws Exception {
        int databaseSizeBeforeUpdate = lareiraRepository.findAll().size();

        // Create the Lareira

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLareiraMockMvc.perform(put("/api/lareiras").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(lareira)))
            .andExpect(status().isBadRequest());

        // Validate the Lareira in the database
        List<Lareira> lareiraList = lareiraRepository.findAll();
        assertThat(lareiraList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLareira() throws Exception {
        // Initialize the database
        lareiraService.save(lareira);

        int databaseSizeBeforeDelete = lareiraRepository.findAll().size();

        // Delete the lareira
        restLareiraMockMvc.perform(delete("/api/lareiras/{id}", lareira.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Lareira> lareiraList = lareiraRepository.findAll();
        assertThat(lareiraList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
