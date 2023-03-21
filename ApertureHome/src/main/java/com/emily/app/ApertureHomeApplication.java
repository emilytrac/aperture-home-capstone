package com.emily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

//scanning appropriate packages to wire up the application
@SpringBootApplication(scanBasePackages = "com.emily")
@EnableJpaRepositories(basePackages = "com.emily.persistence")
public class ApertureHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApertureHomeApplication.class, args);
	}
	
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}

}
