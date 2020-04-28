package br.com.lareira.web.rest;

import br.com.lareira.LareiraAppHomoApp;
import br.com.lareira.domain.Filho;
import br.com.lareira.domain.Casal;
import br.com.lareira.repository.FilhoRepository;
import br.com.lareira.service.FilhoService;
import br.com.lareira.service.dto.FilhoDTO;
import br.com.lareira.service.mapper.FilhoMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.lareira.domain.enumeration.Sexo;
/**
 * Integration tests for the {@link FilhoResource} REST controller.
 */
@SpringBootTest(classes = LareiraAppHomoApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FilhoResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Sexo DEFAULT_SEXO = Sexo.MASCULINO;
    private static final Sexo UPDATED_SEXO = Sexo.FEMININO;

    private static final LocalDate DEFAULT_DATA_NASCIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCIMENTO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private FilhoRepository filhoRepository;

    @Autowired
    private FilhoMapper filhoMapper;

    @Autowired
    private FilhoService filhoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFilhoMockMvc;

    private Filho filho;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Filho createEntity(EntityManager em) {
        Filho filho = new Filho()
            .nome(DEFAULT_NOME)
            .sexo(DEFAULT_SEXO)
            .dataNascimento(DEFAULT_DATA_NASCIMENTO);
        // Add required entity
        Casal casal;
        if (TestUtil.findAll(em, Casal.class).isEmpty()) {
            casal = CasalResourceIT.createEntity(em);
            em.persist(casal);
            em.flush();
        } else {
            casal = TestUtil.findAll(em, Casal.class).get(0);
        }
        filho.setCasal(casal);
        return filho;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Filho createUpdatedEntity(EntityManager em) {
        Filho filho = new Filho()
            .nome(UPDATED_NOME)
            .sexo(UPDATED_SEXO)
            .dataNascimento(UPDATED_DATA_NASCIMENTO);
        // Add required entity
        Casal casal;
        if (TestUtil.findAll(em, Casal.class).isEmpty()) {
            casal = CasalResourceIT.createUpdatedEntity(em);
            em.persist(casal);
            em.flush();
        } else {
            casal = TestUtil.findAll(em, Casal.class).get(0);
        }
        filho.setCasal(casal);
        return filho;
    }

    @BeforeEach
    public void initTest() {
        filho = createEntity(em);
    }

    @Test
    @Transactional
    public void createFilho() throws Exception {
        int databaseSizeBeforeCreate = filhoRepository.findAll().size();

        // Create the Filho
        FilhoDTO filhoDTO = filhoMapper.toDto(filho);
        restFilhoMockMvc.perform(post("/api/filhos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filhoDTO)))
            .andExpect(status().isCreated());

        // Validate the Filho in the database
        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeCreate + 1);
        Filho testFilho = filhoList.get(filhoList.size() - 1);
        assertThat(testFilho.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testFilho.getSexo()).isEqualTo(DEFAULT_SEXO);
        assertThat(testFilho.getDataNascimento()).isEqualTo(DEFAULT_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void createFilhoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = filhoRepository.findAll().size();

        // Create the Filho with an existing ID
        filho.setId(1L);
        FilhoDTO filhoDTO = filhoMapper.toDto(filho);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFilhoMockMvc.perform(post("/api/filhos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filhoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Filho in the database
        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = filhoRepository.findAll().size();
        // set the field null
        filho.setNome(null);

        // Create the Filho, which fails.
        FilhoDTO filhoDTO = filhoMapper.toDto(filho);

        restFilhoMockMvc.perform(post("/api/filhos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filhoDTO)))
            .andExpect(status().isBadRequest());

        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFilhos() throws Exception {
        // Initialize the database
        filhoRepository.saveAndFlush(filho);

        // Get all the filhoList
        restFilhoMockMvc.perform(get("/api/filhos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(filho.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].sexo").value(hasItem(DEFAULT_SEXO.toString())))
            .andExpect(jsonPath("$.[*].dataNascimento").value(hasItem(DEFAULT_DATA_NASCIMENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getFilho() throws Exception {
        // Initialize the database
        filhoRepository.saveAndFlush(filho);

        // Get the filho
        restFilhoMockMvc.perform(get("/api/filhos/{id}", filho.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(filho.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.sexo").value(DEFAULT_SEXO.toString()))
            .andExpect(jsonPath("$.dataNascimento").value(DEFAULT_DATA_NASCIMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFilho() throws Exception {
        // Get the filho
        restFilhoMockMvc.perform(get("/api/filhos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFilho() throws Exception {
        // Initialize the database
        filhoRepository.saveAndFlush(filho);

        int databaseSizeBeforeUpdate = filhoRepository.findAll().size();

        // Update the filho
        Filho updatedFilho = filhoRepository.findById(filho.getId()).get();
        // Disconnect from session so that the updates on updatedFilho are not directly saved in db
        em.detach(updatedFilho);
        updatedFilho
            .nome(UPDATED_NOME)
            .sexo(UPDATED_SEXO)
            .dataNascimento(UPDATED_DATA_NASCIMENTO);
        FilhoDTO filhoDTO = filhoMapper.toDto(updatedFilho);

        restFilhoMockMvc.perform(put("/api/filhos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filhoDTO)))
            .andExpect(status().isOk());

        // Validate the Filho in the database
        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeUpdate);
        Filho testFilho = filhoList.get(filhoList.size() - 1);
        assertThat(testFilho.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testFilho.getSexo()).isEqualTo(UPDATED_SEXO);
        assertThat(testFilho.getDataNascimento()).isEqualTo(UPDATED_DATA_NASCIMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingFilho() throws Exception {
        int databaseSizeBeforeUpdate = filhoRepository.findAll().size();

        // Create the Filho
        FilhoDTO filhoDTO = filhoMapper.toDto(filho);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFilhoMockMvc.perform(put("/api/filhos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(filhoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Filho in the database
        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFilho() throws Exception {
        // Initialize the database
        filhoRepository.saveAndFlush(filho);

        int databaseSizeBeforeDelete = filhoRepository.findAll().size();

        // Delete the filho
        restFilhoMockMvc.perform(delete("/api/filhos/{id}", filho.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Filho> filhoList = filhoRepository.findAll();
        assertThat(filhoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
