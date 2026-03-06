package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Farm;

/**
 * Dto para expor dados de uma fazenda.
 *
 * @param id O id da fazenda cadastrada.
 * @param name O nome da fazenda.
 * @param size o tamanho da fazenda.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Método de conversão Farm para FarmDto.
   *
   * @param farm recebe objeto Farm.
   * @return Objeto FarmDto.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
      farm.getId(),
      farm.getName(),
      farm.getSize()
    );
  }
}
