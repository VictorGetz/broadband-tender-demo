package com.impresssol.broadband.server.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ServerCredentials serverCredentials;
	private final CorsFilter corsFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter, BasicAuthenticationFilter.class)
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/actuator/health").permitAll()
				.antMatchers("/actuator/*").hasRole(ServerRole.ADMIN_ROLE.name())
				.antMatchers("/api/*").hasRole(ServerRole.WEBSERVICE_ROLE.name())
				.antMatchers("/*").hasRole(ServerRole.KAM.name())
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(passwordEncoder())
				.withUser(serverCredentials.getAdminName())
				.password(passwordEncoder().encode(serverCredentials.getAdminPassword()))
				.roles(mapToNames(serverCredentials.getAdminRoles()))
				.and()
				.withUser(serverCredentials.getWebserviceName())
				.password(passwordEncoder().encode(serverCredentials.getWebservicePassword()))
				.roles(mapToNames(serverCredentials.getWebserviceRoles()))
				.and()
				.withUser(serverCredentials.getKamName())
				.password(passwordEncoder().encode(serverCredentials.getKamPassword()))
				.roles(mapToNames(serverCredentials.getKamRoles()));
	}

	private String[] mapToNames(List<ServerRole> roles) {
		return roles.stream().map(Enum::name).toArray(String[]::new);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
