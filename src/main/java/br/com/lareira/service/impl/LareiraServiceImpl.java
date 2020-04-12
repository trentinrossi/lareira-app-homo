package br.com.lareira.service.impl;

import br.com.lareira.service.LareiraService;
import br.com.lareira.domain.Lareira;
import br.com.lareira.repository.LareiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Lareira}.
 */
@Service
@Transactional
public class LareiraServiceImpl implements LareiraService {

    private final Logger log = LoggerFactory.getLogger(LareiraServiceImpl.class);

    private final LareiraRepository lareiraRepository;

    public LareiraServiceImpl(LareiraRepository lareiraRepository) {
        this.lareiraRepository = lareiraRepository;
    }

    /**
     * Save a lareira.
     *
     * @param lareira the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Lareira save(Lareira lareira) {
        log.debug("Request to save Lareira : {}", lareira);
        return lareiraRepository.save(lareira);
    }

    /**
     * Get all the lareiras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Lareira> findAll(Pageable pageable) {
        log.debug("Request to get all Lareiras");
        return lareiraRepository.findAll(pageable);
    }

    /**
     * Get one lareira by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Lareira> findOne(Long id) {
        log.debug("Request to get Lareira : {}", id);
        return lareiraRepository.findById(id);
    }

    /**
     * Delete the lareira by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lareira : {}", id);
        lareiraRepository.deleteById(id);
    }
}
