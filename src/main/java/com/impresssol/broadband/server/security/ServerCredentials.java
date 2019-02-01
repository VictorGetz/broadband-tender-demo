package com.impresssol.broadband.server.security;

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
	Set<ServerRole> adminRoles = Set.of(ServerRole.ADMIN_ROLE, ServerRole.WEBSERVICE_ROLE, ServerRole.KAM);

	String webserviceName;
	String webservicePassword;
	Set<ServerRole> webserviceRole = Set.of(ServerRole.WEBSERVICE_ROLE);


	String kamName;
	String kamPassword;
	Set<ServerRole> kamRole = Set.of(ServerRole.KAM);
}
