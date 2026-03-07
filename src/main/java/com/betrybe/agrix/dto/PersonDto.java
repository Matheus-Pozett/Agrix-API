package com.betrybe.agrix.dto;

import com.betrybe.agrix.model.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * Data Transfer Object for Person entity.
 * This record encapsulates person information for data transfer between layers.
 *
 * @param id the unique identifier of the person
 * @param username the username of the person
 * @param role the role assigned to the person
 */
public record PersonDto(
    Long id,
    String username,
    Role role
) {

  /**
   * Converts a Person entity to a PersonDto.
   *
   * @param person the Person entity to convert
   * @return a PersonDto containing the person's data
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
