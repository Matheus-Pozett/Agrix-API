package com.betrybe.agrix.service;

import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.model.entity.Crop;
import com.betrybe.agrix.model.entity.Farm;
import com.betrybe.agrix.model.repository.CropRepository;
import com.betrybe.agrix.model.repository.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável pela lógica de negócio de uma Fazenda (Farm) no sistema.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Construtor para injeção de dependência do repositório de fazenda.
   *
   * @param farmRepository O repositório a ser injetado.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Método para salvar uma nova Farm no banco de dados.
   *
   * @param farm A fazenda para salvar no banco de dados.
   * @return A entidade persistida, incluindo o ID gerado pelo banco.
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Método para listar todas as fazendas que estão no banco de dados.
   *
   * @return Uma lista com todas as fazendas do sistema.
   */
  public List<Farm> findAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Busca uma fazenda (Farm) pelo id recebido via requisição.
   *
   * @param id O id da fazenda.
   * @return A fazenda encontrada pelo id.
   * @throws FarmNotFoundException caso não encontre a fazenda com id passado.
   */
  public Farm findById(Long id) {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Vincula e salva uma nova plantação no banco de dados.
   *
   * @param farmId O ID da fazenda pai.
   * @param crop A entidade Crop a ser persistida.
   * @return A plantação salva, já contendo o ID gerado e a associação com a fazenda.
   * @throws FarmNotFoundException Caso a fazenda informada não exista.
   */
  public Crop createCrop(Long farmId, Crop crop) {
    Farm farm = findById(farmId);
    farm.addCrop(crop);
    return cropRepository.save(crop);
  }

  /**
   * Recupera a lista de plantações associada a uma fazenda.
   *
   * @param farmId O ID da fazenda.
   * @return A lista de plantações daquela fazenda.
   * @throws FarmNotFoundException Se a fazenda não existir.
   */
  public List<Crop> findAllCropsByFarm(Long farmId) {
    Farm farm = findById(farmId);
    return farm.getCrops();
  }

}
