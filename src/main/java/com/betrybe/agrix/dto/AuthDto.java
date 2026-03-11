package com.betrybe.agrix.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for authentication requests.
 * This record encapsulates the credentials required for user authentication.
 * Both username and password fields are mandatory and cannot be blank.
 *
 * @param username The username of the user attempting to authenticate. Cannot be blank.
 * @param password The password of the user attempting to authenticate. Cannot be blank.
 */
public record AuthDto(
    @NotBlank(message = "Username não pode estar vazio")
    String username,
    @NotBlank(message = "Password não pode estar vazio")
    String password) {
}
