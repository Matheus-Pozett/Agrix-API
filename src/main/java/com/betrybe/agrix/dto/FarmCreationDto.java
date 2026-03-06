package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Farm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Dto para criar uma nova entidade.
 *
 * @param name O nome da fazenda (não pode ser vazio).
 * @param size O tamanho da fazenda (não pode estar vazio e deve ser maior que zero).
 */
public record FarmCreationDto(
    @NotBlank(message = "Nome não pode estar vazio.")
    String name,
    @NotNull(message = "O tamanho é obrigatório.")
    @Positive(message = "O tamanho deve ser maior que zero.")
    Double size) {

  /**
   * Método para transformar FarmCreationDto em uma entidade Farm.
   *
   * @return objeto Farm
   */
  public Farm toEntity() {
    return new Farm(name, size);
  }
}
