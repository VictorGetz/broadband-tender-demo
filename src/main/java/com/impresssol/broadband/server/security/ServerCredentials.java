package com.impresssol.broadband.server.security;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "impresssol.broadband.tender.security")
@Setter
@Getter
public class ServerCredentials {

	String adminName;
	String adminPassword;
	List<ServerRole> adminRoles = Arrays.asList(ServerRole.ADMIN_ROLE, ServerRole.WEBSERVICE_ROLE, ServerRole.KAM);

	String webserviceName;
	String webservicePassword;
	List<ServerRole> webserviceRole = Arrays.asList(ServerRole.WEBSERVICE_ROLE);


	String kamName;
	String kamPassword;
	List<ServerRole> kamRole = Arrays.asList(ServerRole.KAM);
}
