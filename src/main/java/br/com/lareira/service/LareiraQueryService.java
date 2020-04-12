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

import br.com.lareira.domain.Lareira;
import br.com.lareira.domain.*; // for static metamodels
import br.com.lareira.repository.LareiraRepository;
import br.com.lareira.service.dto.LareiraCriteria;

/**
 * Service for executing complex queries for {@link Lareira} entities in the database.
 * The main input is a {@link LareiraCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Lareira} or a {@link Page} of {@link Lareira} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LareiraQueryService extends QueryService<Lareira> {

    private final Logger log = LoggerFactory.getLogger(LareiraQueryService.class);

    private final LareiraRepository lareiraRepository;

    public LareiraQueryService(LareiraRepository lareiraRepository) {
        this.lareiraRepository = lareiraRepository;
    }

    /**
     * Return a {@link List} of {@link Lareira} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Lareira> findByCriteria(LareiraCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Lareira> specification = createSpecification(criteria);
        return lareiraRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Lareira} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Lareira> findByCriteria(LareiraCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Lareira> specification = createSpecification(criteria);
        return lareiraRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LareiraCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Lareira> specification = createSpecification(criteria);
        return lareiraRepository.count(specification);
    }

    /**
     * Function to convert {@link LareiraCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Lareira> createSpecification(LareiraCriteria criteria) {
        Specification<Lareira> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Lareira_.id));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Lareira_.nome));
            }
            if (criteria.getEndereco() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndereco(), Lareira_.endereco));
            }
            if (criteria.getBairro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBairro(), Lareira_.bairro));
            }
            if (criteria.getCep() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCep(), Lareira_.cep));
            }
            if (criteria.getCidade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCidade(), Lareira_.cidade));
            }
            if (criteria.getEstado() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstado(), Lareira_.estado));
            }
            if (criteria.getTelefone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefone(), Lareira_.telefone));
            }
            if (criteria.getCasalId() != null) {
                specification = specification.and(buildSpecification(criteria.getCasalId(),
                    root -> root.join(Lareira_.casals, JoinType.LEFT).get(Casal_.id)));
            }
        }
        return specification;
    }
}
