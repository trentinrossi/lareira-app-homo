package br.com.lareira.service;

import br.com.lareira.service.dto.FilhoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.lareira.domain.Filho}.
 */
public interface FilhoService {

    /**
     * Save a filho.
     *
     * @param filhoDTO the entity to save.
     * @return the persisted entity.
     */
    FilhoDTO save(FilhoDTO filhoDTO);

    /**
     * Get all the filhos.
     *
     * @return the list of entities.
     */
    List<FilhoDTO> findAll();

    /**
     * Get the "id" filho.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FilhoDTO> findOne(Long id);

    /**
     * Delete the "id" filho.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
