package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



/**
 * Service responsible for JWT (JSON Web Token) operations.
 * This service handles the generation of JWT tokens for authenticated users,
 * using the HMAC256 algorithm for signing tokens with a configurable secret key.
 * Generated tokens have a default expiration time of 2 hours.
 */
@Service
public class TokenService {
  private final Algorithm algorithm;

  /**
   * Constructs a TokenService with the specified secret key.
   * The secret key is injected from the application properties and used
   * to create an HMAC256 algorithm instance for token signing.
   *
   * @param secret The secret key used for signing JWT tokens, 
   *               loaded from the property "api.security.token.secret"
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generates a JWT token for the specified username.
   * The token includes the username as the subject and an expiration time
   * of 2 hours from the current time. The token is signed using the HMAC256 algorithm.
   *
   * @param username The username to be included as the subject in the JWT token
   * @return A signed JWT token string
   */
  public String generateToken(String username) {
    return JWT.create().withSubject(username).withExpiresAt(generateExpiration()).sign(algorithm);
  }

  /**
   * Generates the expiration instant for JWT tokens.
   * Tokens expire 2 hours after the current time.
   *
   * @return An Instant representing the expiration time (current time + 2 hours)
   */
  public Instant generateExpiration() {
    return Instant.now().plus(2, ChronoUnit.HOURS);
  }
}
