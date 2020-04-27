package br.com.lareira.repository;

import br.com.lareira.domain.Filho;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Filho entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilhoRepository extends JpaRepository<Filho, Long> {
}
