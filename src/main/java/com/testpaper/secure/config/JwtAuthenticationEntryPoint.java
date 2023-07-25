package com.testpaper.secure.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;

/**
 * The JwtAuthenticationEntryPoint is responsible for handling authentication errors
 * during the JWT authentication process. It implements the AuthenticationEntryPoint interface
 * provided by Spring Security.
*/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	/**
	 * This is to store the serialVersionUID data.
	 */
	private static final long serialVersionUID = -7858869558953243875L;

	/**
	 * This method is called when an authentication error occurs during the JWT authentication process.
	 * It sends an "Unauthorized" error response to the client.
	 *
	 * @param request       The HTTP servlet request.
	 * @param response      The HTTP servlet response.
	 * @param authException The authentication exception.
	 * @throws IOException      If an I/O error occurs while sending the response.
	 * @throws ServletException If a servlet-related error occurs.
	 */
	@Override
	public void commence(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("----JwtAuthenticationEntryPoint----");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
	}
}
