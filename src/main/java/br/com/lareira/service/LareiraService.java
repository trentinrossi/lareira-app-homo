package br.com.lareira.service;

import br.com.lareira.service.dto.LareiraDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.lareira.domain.Lareira}.
 */
public interface LareiraService {

    /**
     * Save a lareira.
     *
     * @param lareiraDTO the entity to save.
     * @return the persisted entity.
     */
    LareiraDTO save(LareiraDTO lareiraDTO);

    /**
     * Get all the lareiras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LareiraDTO> findAll(Pageable pageable);

    /**
     * Get the "id" lareira.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LareiraDTO> findOne(Long id);

    /**
     * Delete the "id" lareira.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
