package com.betrybe.agrix.dto;

/**
 * Data Transfer Object for authentication token responses.
 * This record encapsulates the JWT token returned after successful authentication.
 * The token is used for authorizing subsequent requests to protected endpoints.
 *
 * @param token The JWT (JSON Web Token) string used for authentication and authorization.
 */
public record TokenDto(String token) {
}
