package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.model.entity.Crop;
import com.betrybe.agrix.model.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo gerenciamento de plantações (Crops).
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Lista todas as plantações cadastradas no sistema.
   *
   * @return Lista de DTOs de todas as plantações com status 200 (OK).
   * @see com.betrybe.agrix.service.CropService#findAllCrops()
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.findAllCrops();
    List<CropDto> cropDtos = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropDtos);
  }

  /**
   * Busca uma plantação específica pelo seu ID.
   *
   * @param id O identificador único da plantação.
   * @return DTO da plantação encontrada com status 200 (OK).
   * @see com.betrybe.agrix.service.CropService#findById(Long)
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    return ResponseEntity.ok(CropDto.fromEntity(cropService.findById(id)));
  }

  /**
   * Busca plantações por intervalo de data de colheita.
   *
   * @param start Data inicial do período de colheita (formato: yyyy-MM-dd). Opcional.
   * @param end Data final do período de colheita (formato: yyyy-MM-dd). Opcional.
   * @return Lista de DTOs das plantações com data de colheita dentro do intervalo especificado
   *         com status 200 (OK). Se os parâmetros não forem fornecidos, retorna todas as
   *         plantações.
   * @see com.betrybe.agrix.service.CropService#findByHarvestDate(LocalDate, LocalDate)
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropsByHarvestDate(
      @RequestParam(required = false) LocalDate start,
      @RequestParam(required = false) LocalDate end) {
    List<Crop> crops = cropService.findByHarvestDate(start, end);
    List<CropDto> cropsDto = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropsDto);
  }

  /**
   * Associates a fertilizer with a crop.
   *
   * @param cropId the ID of the crop to associate the fertilizer with
   * @param fertilizerId the ID of the fertilizer to be associated
   * @return ResponseEntity with a success message and HTTP status 201 (CREATED)
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associationCropFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    cropService.associateFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
      .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Retrieves all fertilizers associated with a specific crop.
   *
   * @param cropId the ID of the crop to retrieve fertilizers from
   * @return ResponseEntity with a list of fertilizer DTOs and HTTP status 200 (OK)
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizersByCropId(
      @PathVariable Long cropId
  ) {
    List<Fertilizer> fertilizers = cropService.listAllFertilizersByCrop(cropId);
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();

    return ResponseEntity.ok(fertilizerDtos);
  }
}
