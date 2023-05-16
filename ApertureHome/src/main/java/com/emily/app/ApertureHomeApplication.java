package com.emily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ApertureHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApertureHomeApplication.class, args);
	}

	/* Creating a RestTemplate bean so that the application is able to consume RESTful web
	 * services - this allows the application to call on the APIs from the services
	 * by performing HTTP requests */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
