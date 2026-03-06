package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Crop;
import java.time.LocalDate;


/**
 * Data Transfer Object (DTO) para representação de uma plantação (Crop).
 * <p>
 * Serve para expor os dados da plantação na resposta da API,
 * incluindo o ID da fazenda associada, sem expor a entidade completa.
 * </p>
 *
 * @param id O identificador único da plantação.
 * @param name O nome da plantação.
 * @param plantedArea A área plantada em hectares.
 * @param farmId O ID da fazenda à qual esta plantação pertence.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {
  /**
   * Converte uma entidade {@link Crop} para um {@link CropDto}.
   *
   * @param crop A entidade a ser convertida.
   * @return O DTO correspondente contendo os dados e o ID da fazenda.
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
