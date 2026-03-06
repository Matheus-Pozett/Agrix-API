package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Crop;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;


/**
 * DTO (Data Transfer Object) para a criação de uma nova plantação.
 * <p>
 * Contém as validações de entrada para garantir a integridade dos dados
 * antes de chegarem à camada de serviço.
 * </p>
 *
 * @param name O nome da plantação (Obrigatório).
 * @param plantedArea A área plantada em hectares (Obrigatório e deve ser positiva).
 */
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
   * Converte este DTO para uma entidade {@link Crop}.
   *
   * @return Uma nova instância de Crop com os dados fornecidos.
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
