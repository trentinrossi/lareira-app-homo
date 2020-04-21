package br.com.lareira.service.impl;

import br.com.lareira.service.LareiraService;
import br.com.lareira.domain.Lareira;
import br.com.lareira.repository.LareiraRepository;
import br.com.lareira.service.dto.LareiraDTO;
import br.com.lareira.service.mapper.LareiraMapper;
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

    private final LareiraMapper lareiraMapper;

    public LareiraServiceImpl(LareiraRepository lareiraRepository, LareiraMapper lareiraMapper) {
        this.lareiraRepository = lareiraRepository;
        this.lareiraMapper = lareiraMapper;
    }

    /**
     * Save a lareira.
     *
     * @param lareiraDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LareiraDTO save(LareiraDTO lareiraDTO) {
        log.debug("Request to save Lareira : {}", lareiraDTO);
        Lareira lareira = lareiraMapper.toEntity(lareiraDTO);
        lareira = lareiraRepository.save(lareira);
        return lareiraMapper.toDto(lareira);
    }

    /**
     * Get all the lareiras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LareiraDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lareiras");
        return lareiraRepository.findAll(pageable)
            .map(lareiraMapper::toDto);
    }

    /**
     * Get one lareira by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LareiraDTO> findOne(Long id) {
        log.debug("Request to get Lareira : {}", id);
        return lareiraRepository.findById(id)
            .map(lareiraMapper::toDto);
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
