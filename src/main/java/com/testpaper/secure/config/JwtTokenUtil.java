package com.testpaper.secure.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtTokenUtil is a utility class for handling JSON Web Tokens (JWT) in a Spring Security context.
 * It provides methods for generating and validating JWTs, extracting information from JWTs, and managing token expiration.
 * The class uses the HS512 algorithm for signing the tokens.
*/
@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * This will store the serial version UID value.
	 */
	private static final long serialVersionUID = -2550185165626007488L;
	
	/**
	 * The validity duration of the JWT token in seconds.
	 * Default value is 5 hours.
    */
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	/**
	 * The secret key used for signing the JWT token.
	 * The value is retrieved from the application configuration.
    */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Retrieves the username from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The username extracted from the token.
    */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/**
	 * Retrieves the issued-at date from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The issued-at date extracted from the token.
    */
	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	/**
	 * Retrieves the expiration date from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The expiration date extracted from the token.
    */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/**
	 * Retrieves a specific claim from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @param claimsResolver A function that extracts the desired claim from the token's claims.
	 * @param <T> The type of the claim.
	 * @return The extracted claim from the token.
    */
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Retrieves all claims from a JWT token.
	 * 
	 * @param token The JWT token.
	 * @return The claims extracted from the token.
    */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Checks if a JWT token is expired.
	 * 
	 * @param token The JWT token.
	 * @return True if the token is expired, false otherwise.
    */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * Determines if token expiration should be ignored.
	 * 
	 * @param token The JWT token.
	 * @return True if token expiration should be ignored, false otherwise.
    */
	private Boolean ignoreTokenExpiration(String token) {
		return false;
	}

	/**
	 * Generates a JWT token for the provided user details.
	 * 
	 * @param userDetails The user details.
	 * @return The generated JWT token.
    */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * Generates a JWT token with the specified claims and subject.
	 * 
	 * @param claims The claims to include in the token.
	 * @param subject The subject of the token (typically the username).
	 * @return The generated JWT token.
    */
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * Checks if a JWT token can be refreshed.
	 * A token can be refreshed if it is not expired or if token expiration is ignored.
	 * 
	 * @param token The JWT token.
	 * @return True if the token can be refreshed, false otherwise.
    */
	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	/**
	 * Validates a JWT token.
	 * The token is considered valid if the username extracted from the token matches the username in the user details and the token is not expired.
	 * 
	 * @param token The JWT token.
	 * @param userDetails The user details.
	 * @return True if the token is valid, false otherwise.
    */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
