package com.betrybe.agrix.model.repository;

import com.betrybe.agrix.model.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de acesso aos dados da entidade {@link Farm}.
 * <p>
 * Estende {@link JpaRepository} para fornecer operações
 * padrão de CRUD (Create, Read, Update, Delete)
 * e paginação/ordenação automaticamente, sem necessidade de implementação explícita.
 * </p>
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
