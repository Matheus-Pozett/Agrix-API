package com.betrybe.agrix.exception;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends RuntimeException {
  /**
   * Instantiates a new Farm not found exception.
   */
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }

  /**
   * Instantiates a new Farm not found exception.
   *
   * @param message the message
   */
  public FarmNotFoundException(String message) {
    super(message);
  }
}
