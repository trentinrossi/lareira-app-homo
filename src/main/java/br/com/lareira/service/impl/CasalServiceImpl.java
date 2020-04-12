package br.com.lareira.service.impl;

import br.com.lareira.service.CasalService;
import br.com.lareira.domain.Casal;
import br.com.lareira.repository.CasalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Casal}.
 */
@Service
@Transactional
public class CasalServiceImpl implements CasalService {

    private final Logger log = LoggerFactory.getLogger(CasalServiceImpl.class);

    private final CasalRepository casalRepository;

    public CasalServiceImpl(CasalRepository casalRepository) {
        this.casalRepository = casalRepository;
    }

    /**
     * Save a casal.
     *
     * @param casal the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Casal save(Casal casal) {
        log.debug("Request to save Casal : {}", casal);
        return casalRepository.save(casal);
    }

    /**
     * Get all the casals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Casal> findAll(Pageable pageable) {
        log.debug("Request to get all Casals");
        return casalRepository.findAll(pageable);
    }

    /**
     * Get one casal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Casal> findOne(Long id) {
        log.debug("Request to get Casal : {}", id);
        return casalRepository.findById(id);
    }

    /**
     * Delete the casal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Casal : {}", id);
        casalRepository.deleteById(id);
    }
}
