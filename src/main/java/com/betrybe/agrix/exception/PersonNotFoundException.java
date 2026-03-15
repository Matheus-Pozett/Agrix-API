package com.betrybe.agrix.exception;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("Person not found!");
  }

}
