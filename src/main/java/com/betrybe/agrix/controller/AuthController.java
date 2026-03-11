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

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

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
