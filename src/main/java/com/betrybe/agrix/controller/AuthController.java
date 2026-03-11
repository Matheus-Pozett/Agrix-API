package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.AuthDto;
import com.betrybe.agrix.dto.TokenDto;
import com.betrybe.agrix.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling authentication operations.
 * This controller provides endpoints for user authentication and token generation.
 * It uses Spring Security's AuthenticationManager to validate credentials and
 * generates JWT tokens for authenticated users.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Constructs an AuthController with the required dependencies.
   *
   * @param authenticationManager Spring Security's authentication manager for validating
   * @param tokenService Service responsible for generating JWT tokens
   */
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Authenticates a user and generates a JWT token.
   * This endpoint receives user credentials, validates them using Spring Security,
   * and returns a JWT token that can be used for subsequent authorized requests.
   *
   * @param authDto Data transfer object containing username and password credentials
   * @return ResponseEntity containing a TokenDto with the generated JWT token
   * @throws org.springframework.security.core.AuthenticationException if authentication fails
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(@Valid @RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePass = new UsernamePasswordAuthenticationToken(
          authDto.username(), authDto.password()
    );

    Authentication auth = authenticationManager.authenticate(usernamePass);
    String token = tokenService.generateToken(auth.getName());

    return ResponseEntity.ok(new TokenDto(token));
  }
}
