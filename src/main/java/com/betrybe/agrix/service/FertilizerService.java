package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entity.Fertilizer;
import com.betrybe.agrix.model.repository.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

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
