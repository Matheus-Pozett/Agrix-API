package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Fertilizer;
import jakarta.validation.constraints.NotBlank;

public record FertilizerCreationDto(
    @NotBlank(message = "Nome não pode estar vazio.")
    String name,
    @NotBlank(message = "Marca não pode estar vazio.")
    String brand,
    @NotBlank(message = "Composition não pode estar vazio.")
    String composition
) {

  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}
