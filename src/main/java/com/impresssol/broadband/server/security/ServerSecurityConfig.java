package com.impresssol.broadband.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ServerCredentials serverCredentials;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/actuator/health").permitAll()
				.antMatchers("/actuator/*").hasRole(ServerRole.ADMIN_ROLE.toString())
				.antMatchers("/api/*").hasRole(ServerRole.WEBSERVICE_ROLE.toString())
				.antMatchers("/*").authenticated()
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
				.roles(ServerRole.WEBSERVICE_ROLE.toString(), ServerRole.ADMIN_ROLE.toString())
				.and()
				.withUser(serverCredentials.getWebserviceName())
				.password(passwordEncoder().encode(serverCredentials.getWebservicePassword()))
				.roles(ServerRole.WEBSERVICE_ROLE.toString());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
