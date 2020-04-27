package br.com.lareira.web.rest;

import br.com.lareira.service.FilhoService;
import br.com.lareira.web.rest.errors.BadRequestAlertException;
import br.com.lareira.service.dto.FilhoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.lareira.domain.Filho}.
 */
@RestController
@RequestMapping("/api")
public class FilhoResource {

    private final Logger log = LoggerFactory.getLogger(FilhoResource.class);

    private static final String ENTITY_NAME = "filho";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FilhoService filhoService;

    public FilhoResource(FilhoService filhoService) {
        this.filhoService = filhoService;
    }

    /**
     * {@code POST  /filhos} : Create a new filho.
     *
     * @param filhoDTO the filhoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new filhoDTO, or with status {@code 400 (Bad Request)} if the filho has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/filhos")
    public ResponseEntity<FilhoDTO> createFilho(@Valid @RequestBody FilhoDTO filhoDTO) throws URISyntaxException {
        log.debug("REST request to save Filho : {}", filhoDTO);
        if (filhoDTO.getId() != null) {
            throw new BadRequestAlertException("A new filho cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FilhoDTO result = filhoService.save(filhoDTO);
        return ResponseEntity.created(new URI("/api/filhos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /filhos} : Updates an existing filho.
     *
     * @param filhoDTO the filhoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated filhoDTO,
     * or with status {@code 400 (Bad Request)} if the filhoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the filhoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/filhos")
    public ResponseEntity<FilhoDTO> updateFilho(@Valid @RequestBody FilhoDTO filhoDTO) throws URISyntaxException {
        log.debug("REST request to update Filho : {}", filhoDTO);
        if (filhoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FilhoDTO result = filhoService.save(filhoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, filhoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /filhos} : get all the filhos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of filhos in body.
     */
    @GetMapping("/filhos")
    public List<FilhoDTO> getAllFilhos() {
        log.debug("REST request to get all Filhos");
        return filhoService.findAll();
    }

    /**
     * {@code GET  /filhos/:id} : get the "id" filho.
     *
     * @param id the id of the filhoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the filhoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/filhos/{id}")
    public ResponseEntity<FilhoDTO> getFilho(@PathVariable Long id) {
        log.debug("REST request to get Filho : {}", id);
        Optional<FilhoDTO> filhoDTO = filhoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(filhoDTO);
    }

    /**
     * {@code DELETE  /filhos/:id} : delete the "id" filho.
     *
     * @param id the id of the filhoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/filhos/{id}")
    public ResponseEntity<Void> deleteFilho(@PathVariable Long id) {
        log.debug("REST request to delete Filho : {}", id);
        filhoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
