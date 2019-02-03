package com.impresssol.broadband.server.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerSecurityConfigAnonymousTestIT extends ServerSecurityConfigTestIT{

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ServerCredentials credentials;

	@Test
	public void endpoint_api() {
		ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void endpoint_health() {
		ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}

	@Test
	public void endpoint_env() {
		ResponseEntity<String> response = restTemplate.getForEntity(ENV_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void endpoint_ui() {
		ResponseEntity<String> response = restTemplate.getForEntity(UI_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}
}