package com.impresssol.broadband.seed;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile("seed-auto-apply")
public class SeedAutApplyService implements InitializingBean {

	private final SeedService seedService;

	@Override
	public void afterPropertiesSet() {
		seedService.seedDummyData();
	}
}
