package com.impresssol.broadband.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

	@Value("${impresssol.broadband.tender.repository.endpoint}")
	private String repositoryRestEndpoint;

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		restConfig.setBasePath(repositoryRestEndpoint);
	}
}