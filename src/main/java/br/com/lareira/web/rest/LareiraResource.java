package br.com.lareira.web.rest;

import br.com.lareira.service.LareiraService;
import br.com.lareira.web.rest.errors.BadRequestAlertException;
import br.com.lareira.service.dto.LareiraDTO;
import br.com.lareira.service.dto.LareiraCriteria;
import br.com.lareira.service.LareiraQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.lareira.domain.Lareira}.
 */
@RestController
@RequestMapping("/api")
public class LareiraResource {

    private final Logger log = LoggerFactory.getLogger(LareiraResource.class);

    private static final String ENTITY_NAME = "lareira";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LareiraService lareiraService;

    private final LareiraQueryService lareiraQueryService;

    public LareiraResource(LareiraService lareiraService, LareiraQueryService lareiraQueryService) {
        this.lareiraService = lareiraService;
        this.lareiraQueryService = lareiraQueryService;
    }

    /**
     * {@code POST  /lareiras} : Create a new lareira.
     *
     * @param lareiraDTO the lareiraDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lareiraDTO, or with status {@code 400 (Bad Request)} if the lareira has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lareiras")
    public ResponseEntity<LareiraDTO> createLareira(@Valid @RequestBody LareiraDTO lareiraDTO) throws URISyntaxException {
        log.debug("REST request to save Lareira : {}", lareiraDTO);
        if (lareiraDTO.getId() != null) {
            throw new BadRequestAlertException("A new lareira cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LareiraDTO result = lareiraService.save(lareiraDTO);
        return ResponseEntity.created(new URI("/api/lareiras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lareiras} : Updates an existing lareira.
     *
     * @param lareiraDTO the lareiraDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lareiraDTO,
     * or with status {@code 400 (Bad Request)} if the lareiraDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lareiraDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lareiras")
    public ResponseEntity<LareiraDTO> updateLareira(@Valid @RequestBody LareiraDTO lareiraDTO) throws URISyntaxException {
        log.debug("REST request to update Lareira : {}", lareiraDTO);
        if (lareiraDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LareiraDTO result = lareiraService.save(lareiraDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lareiraDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lareiras} : get all the lareiras.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lareiras in body.
     */
    @GetMapping("/lareiras")
    public ResponseEntity<List<LareiraDTO>> getAllLareiras(LareiraCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Lareiras by criteria: {}", criteria);
        Page<LareiraDTO> page = lareiraQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lareiras/count} : count all the lareiras.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/lareiras/count")
    public ResponseEntity<Long> countLareiras(LareiraCriteria criteria) {
        log.debug("REST request to count Lareiras by criteria: {}", criteria);
        return ResponseEntity.ok().body(lareiraQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /lareiras/:id} : get the "id" lareira.
     *
     * @param id the id of the lareiraDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lareiraDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lareiras/{id}")
    public ResponseEntity<LareiraDTO> getLareira(@PathVariable Long id) {
        log.debug("REST request to get Lareira : {}", id);
        Optional<LareiraDTO> lareiraDTO = lareiraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lareiraDTO);
    }

    /**
     * {@code DELETE  /lareiras/:id} : delete the "id" lareira.
     *
     * @param id the id of the lareiraDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lareiras/{id}")
    public ResponseEntity<Void> deleteLareira(@PathVariable Long id) {
        log.debug("REST request to delete Lareira : {}", id);
        lareiraService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
