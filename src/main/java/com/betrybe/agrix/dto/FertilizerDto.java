package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Fertilizer;

/**
 * DTO para representação de fertilizantes nas respostas da API.
 *
 * @param id Identificador único do fertilizante
 * @param name Nome do fertilizante
 * @param brand Marca do fertilizante
 * @param composition Composição química do fertilizante
 */
public record FertilizerDto(
    Long id, String name, String brand, String composition
) {
  /**
   * Converte uma entidade Fertilizer em um DTO.
   *
   * @param fertilizer Entidade a ser convertida
   * @return DTO com os dados do fertilizante
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
      fertilizer.getId(),
      fertilizer.getName(),
      fertilizer.getBrand(),
      fertilizer.getComposition()
    );
  }
}
