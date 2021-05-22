package ru.xakaton.bimit;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = { "ru.xakaton" })
@EnableJpaRepositories(basePackages = { "ru.xakaton" })
@EnableTransactionManagement
@EnableJpaAuditing
@EnableScheduling
@EnableAsync
public class XakatonBimitEdgeConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
}