package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Fertilizer;

public record FertilizerDto(
    Long id, String name, String brand, String composition
) {
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
    );
  }
}
