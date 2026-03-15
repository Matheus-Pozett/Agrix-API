package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Farm;

public record FarmDto(Long id, String name, Double size) {

  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
      farm.getId(),
      farm.getName(),
      farm.getSize()
    );
  }
}
