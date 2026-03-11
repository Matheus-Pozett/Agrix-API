package com.betrybe.agrix.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(
    @NotBlank(message = "Username não pode estar vazio")
    String username,
    @NotBlank(message = "Password não pode estar vazio")
    String password) {
}
