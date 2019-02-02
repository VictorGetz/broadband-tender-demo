package com.impresssol.broadband.seed;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile("seed-auto-apply")
public class SeedAutApplyService implements InitializingBean {

	private final SeedServiceEndpoint seedServiceEndpoint;

	@Override
	public void afterPropertiesSet() {
		seedServiceEndpoint.seedDummyData();
	}
}
