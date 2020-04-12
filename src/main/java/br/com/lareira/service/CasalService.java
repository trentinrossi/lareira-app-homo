package br.com.lareira.service;

import br.com.lareira.domain.Casal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Casal}.
 */
public interface CasalService {

    /**
     * Save a casal.
     *
     * @param casal the entity to save.
     * @return the persisted entity.
     */
    Casal save(Casal casal);

    /**
     * Get all the casals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Casal> findAll(Pageable pageable);

    /**
     * Get the "id" casal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Casal> findOne(Long id);

    /**
     * Delete the "id" casal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
