package com.betrybe.agrix.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "farm")
@Getter
@Setter
@NoArgsConstructor
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  private List<Crop> crops = new ArrayList<>();

  public Farm(String name, Double size) {
    this.name = name;
    this.size = size;
  }

  public void addCrop(Crop crop) {
    crops.add(crop);
    crop.setFarm(this);
  }
}
