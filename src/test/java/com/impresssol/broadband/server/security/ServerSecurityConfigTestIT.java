package com.impresssol.broadband.server.security;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("security-test")
public abstract class ServerSecurityConfigTestIT {

	static final String HEALTH_ENDPOINT_URL = "/actuator/health";
	static final String ENV_ENDPOINT_URL = "/actuator/env";
	static final String API_ENDPOINT_URL = "/api/projects";
	static final String UI_ENDPOINT_URL = "/";
}