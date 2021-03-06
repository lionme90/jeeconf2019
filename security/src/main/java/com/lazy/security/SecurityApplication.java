package com.lazy.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
public class SecurityApplication {

	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST,
			proxyMode = ScopedProxyMode.TARGET_CLASS)
	public AccessToken accessToken() {
		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
		return ((KeycloakPrincipal) request.getUserPrincipal())
				.getKeycloakSecurityContext().getToken();
	}

	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST,
			proxyMode = ScopedProxyMode.TARGET_CLASS)
	public KeycloakSecurityContext keycloakSecurityContext() {
		HttpServletRequest request =
				((ServletRequestAttributes) RequestContextHolder
						.currentRequestAttributes()).getRequest();
		return ((KeycloakPrincipal) request.getUserPrincipal())
				.getKeycloakSecurityContext();
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
