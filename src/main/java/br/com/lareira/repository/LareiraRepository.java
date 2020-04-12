package br.com.lareira.repository;

import br.com.lareira.domain.Lareira;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lareira entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LareiraRepository extends JpaRepository<Lareira, Long>, JpaSpecificationExecutor<Lareira> {
}
