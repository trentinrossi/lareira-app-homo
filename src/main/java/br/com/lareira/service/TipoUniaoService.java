package br.com.lareira.service;

import br.com.lareira.service.dto.TipoUniaoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.lareira.domain.TipoUniao}.
 */
public interface TipoUniaoService {

    /**
     * Save a tipoUniao.
     *
     * @param tipoUniaoDTO the entity to save.
     * @return the persisted entity.
     */
    TipoUniaoDTO save(TipoUniaoDTO tipoUniaoDTO);

    /**
     * Get all the tipoUniaos.
     *
     * @return the list of entities.
     */
    List<TipoUniaoDTO> findAll();

    /**
     * Get the "id" tipoUniao.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoUniaoDTO> findOne(Long id);

    /**
     * Delete the "id" tipoUniao.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
