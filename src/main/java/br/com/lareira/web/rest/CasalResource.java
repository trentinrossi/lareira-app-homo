package br.com.lareira.web.rest;

import br.com.lareira.domain.Casal;
import br.com.lareira.service.CasalService;
import br.com.lareira.web.rest.errors.BadRequestAlertException;
import br.com.lareira.service.dto.CasalCriteria;
import br.com.lareira.service.CasalQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.lareira.domain.Casal}.
 */
@RestController
@RequestMapping("/api")
public class CasalResource {

    private final Logger log = LoggerFactory.getLogger(CasalResource.class);

    private static final String ENTITY_NAME = "casal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CasalService casalService;

    private final CasalQueryService casalQueryService;

    public CasalResource(CasalService casalService, CasalQueryService casalQueryService) {
        this.casalService = casalService;
        this.casalQueryService = casalQueryService;
    }

    /**
     * {@code POST  /casals} : Create a new casal.
     *
     * @param casal the casal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new casal, or with status {@code 400 (Bad Request)} if the casal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/casals")
    public ResponseEntity<Casal> createCasal(@RequestBody Casal casal) throws URISyntaxException {
        log.debug("REST request to save Casal : {}", casal);
        if (casal.getId() != null) {
            throw new BadRequestAlertException("A new casal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Casal result = casalService.save(casal);
        return ResponseEntity.created(new URI("/api/casals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /casals} : Updates an existing casal.
     *
     * @param casal the casal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casal,
     * or with status {@code 400 (Bad Request)} if the casal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the casal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/casals")
    public ResponseEntity<Casal> updateCasal(@RequestBody Casal casal) throws URISyntaxException {
        log.debug("REST request to update Casal : {}", casal);
        if (casal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Casal result = casalService.save(casal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, casal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /casals} : get all the casals.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of casals in body.
     */
    @GetMapping("/casals")
    public ResponseEntity<List<Casal>> getAllCasals(CasalCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Casals by criteria: {}", criteria);
        Page<Casal> page = casalQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /casals/count} : count all the casals.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/casals/count")
    public ResponseEntity<Long> countCasals(CasalCriteria criteria) {
        log.debug("REST request to count Casals by criteria: {}", criteria);
        return ResponseEntity.ok().body(casalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /casals/:id} : get the "id" casal.
     *
     * @param id the id of the casal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the casal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/casals/{id}")
    public ResponseEntity<Casal> getCasal(@PathVariable Long id) {
        log.debug("REST request to get Casal : {}", id);
        Optional<Casal> casal = casalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casal);
    }

    /**
     * {@code DELETE  /casals/:id} : delete the "id" casal.
     *
     * @param id the id of the casal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/casals/{id}")
    public ResponseEntity<Void> deleteCasal(@PathVariable Long id) {
        log.debug("REST request to delete Casal : {}", id);
        casalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
