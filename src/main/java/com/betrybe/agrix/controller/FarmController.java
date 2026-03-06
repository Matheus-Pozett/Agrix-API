package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmCreationDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.model.entity.Crop;
import com.betrybe.agrix.model.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo gerenciamento de fazendas (Farms) no sistema.
 * Define as rotas e manipula as requisições HTTP relacionadas a fazendas.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  /**
   * Construtor para injeção de dependência do serviço de fazendas.
   *
   * @param farmService O serviço de fazendas a ser injetado.
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Cria uma nova fazenda no sistema.
   * <p>
   * Este endpoint recebe os dados de uma fazenda, valida as informações,
   * salva no banco de dados e retorna a fazenda criada com seu ID gerado.
   * </p>
   *
   * @param farmCreationDto DTO contendo os dados para criação da fazenda (nome e tamanho).
   * @return ResponseEntity contendo o DTO da fazenda criada e status HTTP 201 (Created).
   */
  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody @Valid FarmCreationDto farmCreationDto) {
    Farm farm = farmService.createFarm(farmCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(farm));
  }

  /**
   * Lista todas fazenda no sistema.
   *
   * @return ResponseEntity com status HTTP 200 e o DTO das fazenda com seu id.
   */
  @GetMapping
  public ResponseEntity<List<FarmDto>> listAllFarms() {
    List<Farm> farms = farmService.findAllFarms();
    List<FarmDto> farmDtos = farms.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.ok(farmDtos);
  }

  /**
   * Busca uma fazenda específica pelo seu ID.
   *
   * @param id O identificador único da fazenda.
   * @return ResponseEntity com a fazenda encontrada e status 200 (OK).
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    return ResponseEntity.ok(FarmDto.fromEntity(farmService.findById(id)));
  }

  /**
   * Cria uma nova plantação associada a uma fazenda específica.
   * <p>
   * Este método recebe o ID da fazenda na URL e os dados da plantação no corpo.
   * </p>
   *
   * @param farmId O ID da fazenda onde a plantação será criada.
   * @param cropCreationDto O DTO contendo nome e área da plantação.
   * @return A plantação criada com status 201 (Created).
   * @see com.betrybe.agrix.service.FarmService#createCrop(Long, Crop)
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody @Valid CropCreationDto cropCreationDto) {
    Crop crop = farmService.createCrop(farmId, cropCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(crop));
  }

  /**
   * Lista todas as plantações de uma fazenda específica.
   *
   * @param farmId O ID da fazenda.
   * @return Lista de DTOs das plantações encontradas com status 200 (OK).
   * @see com.betrybe.agrix.service.FarmService#findAllCropsByFarm (Long)
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCrops(@PathVariable Long farmId) {
    List<Crop> crops = farmService.findAllCropsByFarm(farmId);
    List<CropDto> cropDtos = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropDtos);
  }
}
