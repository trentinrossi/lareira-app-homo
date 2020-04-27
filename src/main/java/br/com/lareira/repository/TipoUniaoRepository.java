package br.com.lareira.repository;

import br.com.lareira.domain.TipoUniao;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoUniao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoUniaoRepository extends JpaRepository<TipoUniao, Long> {
}
