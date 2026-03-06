package com.betrybe.agrix.model.repository;

import com.betrybe.agrix.model.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de acesso aos dados da entidade {@link Crop}.
 * <p>
 * Estende {@link JpaRepository} para fornecer operações
 * padrão de CRUD (Create, Read, Update, Delete)
 * e paginação/ordenação automaticamente, sem necessidade de implementação explícita.
 * </p>
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
