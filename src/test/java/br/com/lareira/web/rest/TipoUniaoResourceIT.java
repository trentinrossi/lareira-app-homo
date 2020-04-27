package br.com.lareira.web.rest;

import br.com.lareira.LareiraAppHomoApp;
import br.com.lareira.domain.TipoUniao;
import br.com.lareira.repository.TipoUniaoRepository;
import br.com.lareira.service.TipoUniaoService;
import br.com.lareira.service.dto.TipoUniaoDTO;
import br.com.lareira.service.mapper.TipoUniaoMapper;

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
 * Integration tests for the {@link TipoUniaoResource} REST controller.
 */
@SpringBootTest(classes = LareiraAppHomoApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TipoUniaoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private TipoUniaoRepository tipoUniaoRepository;

    @Autowired
    private TipoUniaoMapper tipoUniaoMapper;

    @Autowired
    private TipoUniaoService tipoUniaoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoUniaoMockMvc;

    private TipoUniao tipoUniao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoUniao createEntity(EntityManager em) {
        TipoUniao tipoUniao = new TipoUniao()
            .nome(DEFAULT_NOME)
            .descricao(DEFAULT_DESCRICAO);
        return tipoUniao;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoUniao createUpdatedEntity(EntityManager em) {
        TipoUniao tipoUniao = new TipoUniao()
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);
        return tipoUniao;
    }

    @BeforeEach
    public void initTest() {
        tipoUniao = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoUniao() throws Exception {
        int databaseSizeBeforeCreate = tipoUniaoRepository.findAll().size();

        // Create the TipoUniao
        TipoUniaoDTO tipoUniaoDTO = tipoUniaoMapper.toDto(tipoUniao);
        restTipoUniaoMockMvc.perform(post("/api/tipo-uniaos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUniaoDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoUniao in the database
        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoUniao testTipoUniao = tipoUniaoList.get(tipoUniaoList.size() - 1);
        assertThat(testTipoUniao.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testTipoUniao.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createTipoUniaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoUniaoRepository.findAll().size();

        // Create the TipoUniao with an existing ID
        tipoUniao.setId(1L);
        TipoUniaoDTO tipoUniaoDTO = tipoUniaoMapper.toDto(tipoUniao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoUniaoMockMvc.perform(post("/api/tipo-uniaos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUniaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUniao in the database
        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoUniaoRepository.findAll().size();
        // set the field null
        tipoUniao.setNome(null);

        // Create the TipoUniao, which fails.
        TipoUniaoDTO tipoUniaoDTO = tipoUniaoMapper.toDto(tipoUniao);

        restTipoUniaoMockMvc.perform(post("/api/tipo-uniaos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUniaoDTO)))
            .andExpect(status().isBadRequest());

        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoUniaos() throws Exception {
        // Initialize the database
        tipoUniaoRepository.saveAndFlush(tipoUniao);

        // Get all the tipoUniaoList
        restTipoUniaoMockMvc.perform(get("/api/tipo-uniaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoUniao.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getTipoUniao() throws Exception {
        // Initialize the database
        tipoUniaoRepository.saveAndFlush(tipoUniao);

        // Get the tipoUniao
        restTipoUniaoMockMvc.perform(get("/api/tipo-uniaos/{id}", tipoUniao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoUniao.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingTipoUniao() throws Exception {
        // Get the tipoUniao
        restTipoUniaoMockMvc.perform(get("/api/tipo-uniaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoUniao() throws Exception {
        // Initialize the database
        tipoUniaoRepository.saveAndFlush(tipoUniao);

        int databaseSizeBeforeUpdate = tipoUniaoRepository.findAll().size();

        // Update the tipoUniao
        TipoUniao updatedTipoUniao = tipoUniaoRepository.findById(tipoUniao.getId()).get();
        // Disconnect from session so that the updates on updatedTipoUniao are not directly saved in db
        em.detach(updatedTipoUniao);
        updatedTipoUniao
            .nome(UPDATED_NOME)
            .descricao(UPDATED_DESCRICAO);
        TipoUniaoDTO tipoUniaoDTO = tipoUniaoMapper.toDto(updatedTipoUniao);

        restTipoUniaoMockMvc.perform(put("/api/tipo-uniaos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUniaoDTO)))
            .andExpect(status().isOk());

        // Validate the TipoUniao in the database
        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeUpdate);
        TipoUniao testTipoUniao = tipoUniaoList.get(tipoUniaoList.size() - 1);
        assertThat(testTipoUniao.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testTipoUniao.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoUniao() throws Exception {
        int databaseSizeBeforeUpdate = tipoUniaoRepository.findAll().size();

        // Create the TipoUniao
        TipoUniaoDTO tipoUniaoDTO = tipoUniaoMapper.toDto(tipoUniao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoUniaoMockMvc.perform(put("/api/tipo-uniaos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoUniaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoUniao in the database
        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoUniao() throws Exception {
        // Initialize the database
        tipoUniaoRepository.saveAndFlush(tipoUniao);

        int databaseSizeBeforeDelete = tipoUniaoRepository.findAll().size();

        // Delete the tipoUniao
        restTipoUniaoMockMvc.perform(delete("/api/tipo-uniaos/{id}", tipoUniao.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoUniao> tipoUniaoList = tipoUniaoRepository.findAll();
        assertThat(tipoUniaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
