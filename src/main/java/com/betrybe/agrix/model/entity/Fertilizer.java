package com.betrybe.agrix.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a fertilizer.
 *
 * <p>A fertilizer has a name, brand, and composition, and can be associated
 * with multiple crops through a many-to-many relationship.</p>
 */
@Entity
@Table(name = "fertilizer")
@Getter
@Setter
@NoArgsConstructor
public class Fertilizer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String brand;
  private String composition;

  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops;

  /**
   * Constructs a new Fertilizer with the specified details.
   *
   * @param name the name of the fertilizer
   * @param brand the brand of the fertilizer
   * @param composition the chemical composition of the fertilizer
   */
  public Fertilizer(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }
}
