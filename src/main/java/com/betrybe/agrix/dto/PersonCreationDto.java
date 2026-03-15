package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Person;
import com.betrybe.agrix.security.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonCreationDto(
    @NotBlank(message = "Nome não pode ser vazio ou nulo!")
    String username,
    @NotBlank(message = "Senha não pode ser vazio ou nulo!")
    String password,
    @NotNull(message = "Função não pode ser vazio ou nulo!")
    Role role
) {
  /**
   * Converts this DTO to a Person entity.
   *
   * @return a new Person entity with the data from this DTO
   */
  public Person toEntity() {
    return new Person(username, password, role);
  }
}
