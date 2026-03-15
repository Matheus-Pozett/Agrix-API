package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.PersonCreationDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.model.entity.Person;
import com.betrybe.agrix.service.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  public ResponseEntity<PersonDto> createPerson(
      @Valid @RequestBody PersonCreationDto personCreationDto
  ) {
    Person person = personService.create(personCreationDto.toEntity());
    PersonDto personDto = PersonDto.fromEntity(person);

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
  }

  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<PersonDto>> getAllPersons() {
    List<Person> persons = personService.getAllPersons();
    List<PersonDto> personDtos = persons.stream().map(PersonDto::fromEntity).toList();
    return ResponseEntity.ok(personDtos);
  }
}
