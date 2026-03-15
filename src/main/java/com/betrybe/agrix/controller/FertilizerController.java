package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerCreationDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.model.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
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
@RequestMapping("/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(
      @Valid @RequestBody FertilizerCreationDto fertilizerCreationDto
  ) {
    Fertilizer fertilizer = fertilizerService.createFertilizer(fertilizerCreationDto.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.fromEntity(fertilizer));
  }

  @GetMapping
  public ResponseEntity<List<FertilizerDto>> listAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.findAllFertilizers();
    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
        .map(FertilizerDto::fromEntity).toList();

    return ResponseEntity.ok(fertilizerDtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> listFertilizerById(@PathVariable Long id) {
    return ResponseEntity.ok(FertilizerDto.fromEntity(fertilizerService.findById(id)));
  }
}
