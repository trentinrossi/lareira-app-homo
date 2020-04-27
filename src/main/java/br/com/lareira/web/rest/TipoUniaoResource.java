package br.com.lareira.web.rest;

import br.com.lareira.service.TipoUniaoService;
import br.com.lareira.web.rest.errors.BadRequestAlertException;
import br.com.lareira.service.dto.TipoUniaoDTO;

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
 * REST controller for managing {@link br.com.lareira.domain.TipoUniao}.
 */
@RestController
@RequestMapping("/api")
public class TipoUniaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoUniaoResource.class);

    private static final String ENTITY_NAME = "tipoUniao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoUniaoService tipoUniaoService;

    public TipoUniaoResource(TipoUniaoService tipoUniaoService) {
        this.tipoUniaoService = tipoUniaoService;
    }

    /**
     * {@code POST  /tipo-uniaos} : Create a new tipoUniao.
     *
     * @param tipoUniaoDTO the tipoUniaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoUniaoDTO, or with status {@code 400 (Bad Request)} if the tipoUniao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-uniaos")
    public ResponseEntity<TipoUniaoDTO> createTipoUniao(@Valid @RequestBody TipoUniaoDTO tipoUniaoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoUniao : {}", tipoUniaoDTO);
        if (tipoUniaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoUniao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoUniaoDTO result = tipoUniaoService.save(tipoUniaoDTO);
        return ResponseEntity.created(new URI("/api/tipo-uniaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-uniaos} : Updates an existing tipoUniao.
     *
     * @param tipoUniaoDTO the tipoUniaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoUniaoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoUniaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoUniaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-uniaos")
    public ResponseEntity<TipoUniaoDTO> updateTipoUniao(@Valid @RequestBody TipoUniaoDTO tipoUniaoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoUniao : {}", tipoUniaoDTO);
        if (tipoUniaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoUniaoDTO result = tipoUniaoService.save(tipoUniaoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoUniaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-uniaos} : get all the tipoUniaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoUniaos in body.
     */
    @GetMapping("/tipo-uniaos")
    public List<TipoUniaoDTO> getAllTipoUniaos() {
        log.debug("REST request to get all TipoUniaos");
        return tipoUniaoService.findAll();
    }

    /**
     * {@code GET  /tipo-uniaos/:id} : get the "id" tipoUniao.
     *
     * @param id the id of the tipoUniaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoUniaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-uniaos/{id}")
    public ResponseEntity<TipoUniaoDTO> getTipoUniao(@PathVariable Long id) {
        log.debug("REST request to get TipoUniao : {}", id);
        Optional<TipoUniaoDTO> tipoUniaoDTO = tipoUniaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoUniaoDTO);
    }

    /**
     * {@code DELETE  /tipo-uniaos/:id} : delete the "id" tipoUniao.
     *
     * @param id the id of the tipoUniaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-uniaos/{id}")
    public ResponseEntity<Void> deleteTipoUniao(@PathVariable Long id) {
        log.debug("REST request to delete TipoUniao : {}", id);
        tipoUniaoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
