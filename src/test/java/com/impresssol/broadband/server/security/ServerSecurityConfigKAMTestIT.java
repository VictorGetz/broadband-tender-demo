package com.impresssol.broadband.server.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerSecurityConfigKAMTestIT extends ServerSecurityConfigTestIT{

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ServerCredentials credentials;

	@Test
	public void endpoint_env() {
		restTemplate = restTemplate.withBasicAuth(credentials.getKamName(), credentials.getKamPassword());
		ResponseEntity<String> response = restTemplate.getForEntity(ENV_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void endpoint_health() {
		restTemplate = restTemplate.withBasicAuth(credentials.getKamName(), credentials.getKamPassword());
		ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}

	@Test
	public void endpoint_api() {
		restTemplate = restTemplate.withBasicAuth(credentials.getKamName(), credentials.getKamPassword());
		ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.FORBIDDEN);
	}

	@Test
	public void endpoint_ui() {
		restTemplate = restTemplate.withBasicAuth(credentials.getKamName(), credentials.getKamPassword());

		ResponseEntity<String> response = restTemplate.getForEntity(UI_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.NOT_FOUND,HttpStatus.OK);
	}
}