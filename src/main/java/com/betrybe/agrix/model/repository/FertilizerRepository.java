package com.betrybe.agrix.model.repository;

import com.betrybe.agrix.model.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Fertilizer entities.
 *
 * <p>Extends JpaRepository to provide CRUD operations and query methods
 * for the Fertilizer entity.</p>
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
