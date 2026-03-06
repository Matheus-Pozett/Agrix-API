package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Fertilizer;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para criação de fertilizantes.
 *
 * @param name Nome do fertilizante
 * @param brand Marca do fertilizante
 * @param composition Composição química do fertilizante
 */
public record FertilizerCreationDto(
    @NotBlank(message = "Nome não pode estar vazio.")
    String name,
    @NotBlank(message = "Marca não pode estar vazio.")
    String brand,
    @NotBlank(message = "Composition não pode estar vazio.")
    String composition
) {

  /**
   * Converte o DTO em uma entidade Fertilizer.
   *
   * @return Entidade Fertilizer com os dados do DTO
   */
  public Fertilizer toEntity() {
    return new Fertilizer(name, brand, composition);
  }
}
