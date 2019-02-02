package com.impresssol.broadband.server.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("security-test")
public class ServerSecurityConfigTestIT {

	private static final String HEALTH_ENDPOINT_URL = "/actuator/health";
	private static final String ENV_ENDPOINT_URL = "/actuator/env";
	private static final String API_ENDPOINT_URL = "/api/projects";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ServerCredentials credentials;

	@Test
	public void testAdminRole_endpoint_health() {
		restTemplate = restTemplate.withBasicAuth(credentials.getAdminName(), credentials.getAdminPassword());

		ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}

	@Test
	public void testAdminRole_endpoint_env() {
		restTemplate = restTemplate.withBasicAuth(credentials.getAdminName(), credentials.getAdminPassword());

		ResponseEntity<String> response = restTemplate.getForEntity(ENV_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void testAdminRole_endpoint_api() {
		restTemplate = restTemplate.withBasicAuth(credentials.getAdminName(), credentials.getAdminPassword());

		ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}


	@Test
	public void anonymousUser_endpoint_api() {
		ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void anonymousUser_endpoint_health() {
		ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}

	@Test
	public void anonymousUser_endpoint_env() {
		ResponseEntity<String> response = restTemplate.getForEntity(ENV_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void webserviceRole_endpoint_env() {
		restTemplate = restTemplate.withBasicAuth(credentials.getWebserviceName(), credentials.getWebservicePassword());
		ResponseEntity<String> response = restTemplate.getForEntity(ENV_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

	@Test
	public void webserviceRole_endpoint_health() {
		restTemplate = restTemplate.withBasicAuth(credentials.getWebserviceName(), credentials.getWebservicePassword());
		ResponseEntity<String> response = restTemplate.getForEntity(HEALTH_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}

	@Test
	public void webserviceRole_endpoint_api() {
		restTemplate = restTemplate.withBasicAuth(credentials.getWebserviceName(), credentials.getWebservicePassword());
		ResponseEntity<String> response = restTemplate.getForEntity(API_ENDPOINT_URL, String.class);

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isIn(HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.OK);
	}
}