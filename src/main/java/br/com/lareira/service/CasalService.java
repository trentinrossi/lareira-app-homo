package br.com.lareira.service;

import br.com.lareira.service.dto.CasalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.lareira.domain.Casal}.
 */
public interface CasalService {

    /**
     * Save a casal.
     *
     * @param casalDTO the entity to save.
     * @return the persisted entity.
     */
    CasalDTO save(CasalDTO casalDTO);

    /**
     * Get all the casals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CasalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" casal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CasalDTO> findOne(Long id);

    /**
     * Delete the "id" casal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
