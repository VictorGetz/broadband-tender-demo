package com.impresssol.broadband.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.impresssol.broadband.data")
@EntityScan(basePackages = "com.impresssol.broadband.data.entities")
@EnableTransactionManagement
public class DataModelConfig {

}