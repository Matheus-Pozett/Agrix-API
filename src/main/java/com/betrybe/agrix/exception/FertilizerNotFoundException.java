package com.betrybe.agrix.exception;

public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizer not found!");
  }

  public FertilizerNotFoundException(String message) {
    super(message);
  }
}
