package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entity.Crop;
import com.betrybe.agrix.model.entity.Fertilizer;
import com.betrybe.agrix.model.repository.CropRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela lógica de negócio de plantações (Crops).
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerService fertilizerService;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Busca todas as plantações registradas no banco de dados.
   *
   * @return Lista de todas as plantações.
   */
  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Busca uma plantação por ID.
   *
   * @param cropId O identificador da plantação.
   * @return A entidade Crop encontrada no banco de dados.
   * @throws CropNotFoundException Caso a plantação não exista.
   */
  public Crop findById(Long cropId) {
    return cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Busca plantações por intervalo de data de colheita.
   *
   * @param start Data inicial do período de colheita.
   * @param end Data final do período de colheita.
   * @return Lista de plantações com data de colheita entre as datas especificadas (inclusive).
   */
  public List<Crop> findByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Associates a fertilizer with a crop.
   *
   * @param cropId the ID of the crop to associate the fertilizer with
   * @param fertilizerId the ID of the fertilizer to be associated
   * @throws CropNotFoundException if the crop with the given ID is not found
   * @throws FertilizerNotFoundException if the fertilizer with the given ID is not found
   */
  public void associateFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);
    crop.addFertilizer(fertilizer);
    cropRepository.save(crop);
  }

  public List<Fertilizer> listAllFertilizersByCrop(Long id) {
    Crop crop = findById(id);
    return crop.getFertilizers();
  }
}
