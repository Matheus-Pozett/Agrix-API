package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Farm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FarmCreationDto(
    @NotBlank(message = "Nome não pode estar vazio.")
    String name,
    @NotNull(message = "O tamanho é obrigatório.")
    @Positive(message = "O tamanho deve ser maior que zero.")
    Double size) {
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
