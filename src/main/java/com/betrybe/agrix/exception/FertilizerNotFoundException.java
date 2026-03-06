package com.betrybe.agrix.exception;

/**
 * The type Fertilizer not found exception.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }

  public FertilizerNotFoundException(String message) {
    super(message);
  }
}
