package br.com.lareira.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import br.com.lareira.domain.Casal;
import br.com.lareira.domain.*; // for static metamodels
import br.com.lareira.repository.CasalRepository;
import br.com.lareira.service.dto.CasalCriteria;
import br.com.lareira.service.dto.CasalDTO;
import br.com.lareira.service.mapper.CasalMapper;

/**
 * Service for executing complex queries for {@link Casal} entities in the database.
 * The main input is a {@link CasalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CasalDTO} or a {@link Page} of {@link CasalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CasalQueryService extends QueryService<Casal> {

    private final Logger log = LoggerFactory.getLogger(CasalQueryService.class);

    private final CasalRepository casalRepository;

    private final CasalMapper casalMapper;

    public CasalQueryService(CasalRepository casalRepository, CasalMapper casalMapper) {
        this.casalRepository = casalRepository;
        this.casalMapper = casalMapper;
    }

    /**
     * Return a {@link List} of {@link CasalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CasalDTO> findByCriteria(CasalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Casal> specification = createSpecification(criteria);
        return casalMapper.toDto(casalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CasalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CasalDTO> findByCriteria(CasalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Casal> specification = createSpecification(criteria);
        return casalRepository.findAll(specification, page)
            .map(casalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CasalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Casal> specification = createSpecification(criteria);
        return casalRepository.count(specification);
    }

    /**
     * Function to convert {@link CasalCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Casal> createSpecification(CasalCriteria criteria) {
        Specification<Casal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Casal_.id));
            }
            if (criteria.getMaridoNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoNome(), Casal_.maridoNome));
            }
            if (criteria.getMaridoSobrenome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoSobrenome(), Casal_.maridoSobrenome));
            }
            if (criteria.getMaridoDataNascimento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaridoDataNascimento(), Casal_.maridoDataNascimento));
            }
            if (criteria.getMaridoProfissao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoProfissao(), Casal_.maridoProfissao));
            }
            if (criteria.getMaridoTelCelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoTelCelular(), Casal_.maridoTelCelular));
            }
            if (criteria.getMaridoEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoEmail(), Casal_.maridoEmail));
            }
            if (criteria.getMaridoProblemaSaude() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaridoProblemaSaude(), Casal_.maridoProblemaSaude));
            }
            if (criteria.getEsposaNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaNome(), Casal_.esposaNome));
            }
            if (criteria.getEsposaSobrenome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaSobrenome(), Casal_.esposaSobrenome));
            }
            if (criteria.getEsposaDataNascimento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEsposaDataNascimento(), Casal_.esposaDataNascimento));
            }
            if (criteria.getEsposaProfissao() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaProfissao(), Casal_.esposaProfissao));
            }
            if (criteria.getEsposaTelCelular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaTelCelular(), Casal_.esposaTelCelular));
            }
            if (criteria.getEsposaEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaEmail(), Casal_.esposaEmail));
            }
            if (criteria.getEsposaProblemaSaude() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsposaProblemaSaude(), Casal_.esposaProblemaSaude));
            }
            if (criteria.getIdLareiraId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdLareiraId(),
                    root -> root.join(Casal_.idLareira, JoinType.LEFT).get(Lareira_.id)));
            }
        }
        return specification;
    }
}
