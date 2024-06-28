package com.webtech.football.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

// Sollte für API genutzt werden
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
