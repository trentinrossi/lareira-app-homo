package br.com.lareira.service.impl;

import br.com.lareira.service.TipoUniaoService;
import br.com.lareira.domain.TipoUniao;
import br.com.lareira.repository.TipoUniaoRepository;
import br.com.lareira.service.dto.TipoUniaoDTO;
import br.com.lareira.service.mapper.TipoUniaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TipoUniao}.
 */
@Service
@Transactional
public class TipoUniaoServiceImpl implements TipoUniaoService {

    private final Logger log = LoggerFactory.getLogger(TipoUniaoServiceImpl.class);

    private final TipoUniaoRepository tipoUniaoRepository;

    private final TipoUniaoMapper tipoUniaoMapper;

    public TipoUniaoServiceImpl(TipoUniaoRepository tipoUniaoRepository, TipoUniaoMapper tipoUniaoMapper) {
        this.tipoUniaoRepository = tipoUniaoRepository;
        this.tipoUniaoMapper = tipoUniaoMapper;
    }

    /**
     * Save a tipoUniao.
     *
     * @param tipoUniaoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TipoUniaoDTO save(TipoUniaoDTO tipoUniaoDTO) {
        log.debug("Request to save TipoUniao : {}", tipoUniaoDTO);
        TipoUniao tipoUniao = tipoUniaoMapper.toEntity(tipoUniaoDTO);
        tipoUniao = tipoUniaoRepository.save(tipoUniao);
        return tipoUniaoMapper.toDto(tipoUniao);
    }

    /**
     * Get all the tipoUniaos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TipoUniaoDTO> findAll() {
        log.debug("Request to get all TipoUniaos");
        return tipoUniaoRepository.findAll().stream()
            .map(tipoUniaoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one tipoUniao by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoUniaoDTO> findOne(Long id) {
        log.debug("Request to get TipoUniao : {}", id);
        return tipoUniaoRepository.findById(id)
            .map(tipoUniaoMapper::toDto);
    }

    /**
     * Delete the tipoUniao by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoUniao : {}", id);
        tipoUniaoRepository.deleteById(id);
    }
}
