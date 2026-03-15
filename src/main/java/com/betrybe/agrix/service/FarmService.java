package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entity.Crop;
import com.betrybe.agrix.model.entity.Farm;
import com.betrybe.agrix.model.repository.CropRepository;
import com.betrybe.agrix.model.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> findAllFarms() {
    return farmRepository.findAll();
  }

  public Farm findById(Long id) {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Associates and saves a new crop in the database.
   *
   * @param farmId The ID of the parent farm.
   * @param crop The Crop entity to be persisted.
   * @return The saved crop, containing the generated ID and association with the farm.
   * @throws FarmNotFoundException If the specified farm does not exist.
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = findById(farmId);
    farm.addCrop(crop);
    return cropRepository.save(crop);
  }

  /**
   * Retrieves the list of crops associated with a farm.
   *
   * @param farmId The ID of the farm.
   * @return The list of crops for that farm.
   * @throws FarmNotFoundException If the farm does not exist.
   */
  public List<Crop> findAllCropsByFarm(Long farmId) {
    Farm farm = findById(farmId);
    return farm.getCrops();
  }

}
