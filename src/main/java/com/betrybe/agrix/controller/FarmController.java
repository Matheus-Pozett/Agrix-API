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

@RestController
@RequestMapping("/farms")
public class FarmController {
  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody @Valid FarmCreationDto farmCreationDto) {
    Farm farm = farmService.createFarm(farmCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(farm));
  }

  @GetMapping
  public ResponseEntity<List<FarmDto>> listAllFarms() {
    List<Farm> farms = farmService.findAllFarms();
    List<FarmDto> farmDtos = farms.stream().map(FarmDto::fromEntity).toList();
    return ResponseEntity.ok(farmDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    return ResponseEntity.ok(FarmDto.fromEntity(farmService.findById(id)));
  }

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody @Valid CropCreationDto cropCreationDto) {
    Crop crop = farmService.createCrop(farmId, cropCreationDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.fromEntity(crop));
  }

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getAllCrops(@PathVariable Long farmId) {
    List<Crop> crops = farmService.findAllCropsByFarm(farmId);
    List<CropDto> cropDtos = crops.stream().map(CropDto::fromEntity).toList();
    return ResponseEntity.ok(cropDtos);
  }
}
