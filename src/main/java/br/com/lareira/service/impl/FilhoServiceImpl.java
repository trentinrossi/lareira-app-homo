package br.com.lareira.service.impl;

import br.com.lareira.service.FilhoService;
import br.com.lareira.domain.Filho;
import br.com.lareira.repository.FilhoRepository;
import br.com.lareira.service.dto.FilhoDTO;
import br.com.lareira.service.mapper.FilhoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Filho}.
 */
@Service
@Transactional
public class FilhoServiceImpl implements FilhoService {

    private final Logger log = LoggerFactory.getLogger(FilhoServiceImpl.class);

    private final FilhoRepository filhoRepository;

    private final FilhoMapper filhoMapper;

    public FilhoServiceImpl(FilhoRepository filhoRepository, FilhoMapper filhoMapper) {
        this.filhoRepository = filhoRepository;
        this.filhoMapper = filhoMapper;
    }

    /**
     * Save a filho.
     *
     * @param filhoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FilhoDTO save(FilhoDTO filhoDTO) {
        log.debug("Request to save Filho : {}", filhoDTO);
        Filho filho = filhoMapper.toEntity(filhoDTO);
        filho = filhoRepository.save(filho);
        return filhoMapper.toDto(filho);
    }

    /**
     * Get all the filhos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilhoDTO> findAll() {
        log.debug("Request to get all Filhos");
        return filhoRepository.findAll().stream()
            .map(filhoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one filho by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FilhoDTO> findOne(Long id) {
        log.debug("Request to get Filho : {}", id);
        return filhoRepository.findById(id)
            .map(filhoMapper::toDto);
    }

    /**
     * Delete the filho by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Filho : {}", id);
        filhoRepository.deleteById(id);
    }
}
