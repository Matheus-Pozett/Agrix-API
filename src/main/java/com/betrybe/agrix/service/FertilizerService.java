package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entity.Fertilizer;
import com.betrybe.agrix.model.repository.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing fertilizer business logic.
 *
 * <p>Handles operations related to fertilizers, including creation,
 * retrieval, and association with crops.</p>
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Creates and persists a new fertilizer.
   *
   * @param fertilizer the fertilizer entity to be created
   * @return the created fertilizer with generated ID
   */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> findAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer findById(Long id) {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }
}
