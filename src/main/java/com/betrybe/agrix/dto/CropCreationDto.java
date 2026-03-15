package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Crop;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public record CropCreationDto(
    @NotBlank(message = "Nome não pode estar vazio.")
    String name,
    @NotNull(message = "A área é obrigatório.")
    @Positive(message = "O tamanho deve ser maior que zero.")
    Double plantedArea,
    @NotNull
    LocalDate plantedDate,
    @NotNull
    LocalDate harvestDate
) {
  /**
   * Converts this DTO to a {@link Crop} entity.
   *
   * @return A new instance of Crop with the provided data.
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
