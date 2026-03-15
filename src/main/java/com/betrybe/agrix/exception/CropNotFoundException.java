package com.betrybe.agrix.exception;

public class CropNotFoundException extends RuntimeException {

  public CropNotFoundException() {
    super("Plantation not found!");
  }

  public CropNotFoundException(String message) {
    super(message);
  }
}
