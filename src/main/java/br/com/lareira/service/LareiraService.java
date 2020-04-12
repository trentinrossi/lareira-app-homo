package br.com.lareira.service;

import br.com.lareira.domain.Lareira;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Lareira}.
 */
public interface LareiraService {

    /**
     * Save a lareira.
     *
     * @param lareira the entity to save.
     * @return the persisted entity.
     */
    Lareira save(Lareira lareira);

    /**
     * Get all the lareiras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Lareira> findAll(Pageable pageable);

    /**
     * Get the "id" lareira.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Lareira> findOne(Long id);

    /**
     * Delete the "id" lareira.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
