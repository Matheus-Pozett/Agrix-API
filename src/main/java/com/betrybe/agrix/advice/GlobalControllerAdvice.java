package com.betrybe.agrix.advice;

import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador global de exceções.
 * Responsável por interceptar erros em toda a aplicação e formatar a resposta JSON.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Trata exceções de validação (ex: campos inválidos no DTO).
   *
   * @param e A exceção capturada contendo os erros de validação.
   * @return ResponseEntity com status 400 (Bad Request) e a mensagem do primeiro erro encontrado.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handlerMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    String errorMessage = Objects.requireNonNull(e.getBindingResult()
        .getFieldError()).getDefaultMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

  /**
   * Trata a exceção de negócio quando uma fazenda não é encontrada.
   *
   * @param e A exceção capturada contendo a mensagem de erro.
   * @return ResponseEntity com status 404 (Not Found) e a mensagem explicativa.
   */
  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handlerFarmNotFoundException(FarmNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  /**
   * Trata a exceção de negócio quando uma plantação não é encontrada.
   *
   * @param e A exceção capturada contendo a mensagem de erro.
   * @return ResponseEntity com status 404 (Not Found) e a mensagem explicativa.
   */
  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handlerCropNotFoundException(CropNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  /**
   * Trata a exceção de negócio quando um fertilizante não é encontrado.
   *
   * @param e A exceção capturada contendo a mensagem de erro.
   * @return ResponseEntity com status 404 (Not Found) e a mensagem explicativa.
   */
  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handlerCropNotFoundException(FertilizerNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<String> handlerBadCredentialsException(BadCredentialsException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
  }
  /**
   * Trata todas as exceções não esperadas (bugs, falhas de conexão, etc.).
   * Funciona como um "catch-all" de segurança.
   *
   * @param exception A exceção genérica capturada.
   * @return ResponseEntity com status 500 (Internal Server Error).
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(exception.getMessage());
  }
}
