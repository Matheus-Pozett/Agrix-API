package com.betrybe.agrix.exception;

public class FarmNotFoundException extends RuntimeException {

  public FarmNotFoundException() {
    super("Farm not found!");
  }

  public FarmNotFoundException(String message) {
    super(message);
  }
}
