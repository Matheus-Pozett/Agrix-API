package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Crop;
import java.time.LocalDate;

public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {
  /**
   * Converts a {@link Crop} entity to a {@link CropDto}.
   *
   * @param crop The entity to be converted.
   * @return The corresponding DTO containing the data and the farm ID.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
      crop.getId(),
      crop.getName(),
      crop.getPlantedArea(),
      crop.getPlantedDate(),
      crop.getHarvestDate(),
      crop.getFarm().getId()
    );
  }
}
