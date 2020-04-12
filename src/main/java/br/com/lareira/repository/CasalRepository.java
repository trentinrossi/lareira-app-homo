package br.com.lareira.repository;

import br.com.lareira.domain.Casal;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Casal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasalRepository extends JpaRepository<Casal, Long>, JpaSpecificationExecutor<Casal> {
}
