package com.emily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/* scanning appropriate packages to auto configure the application - this is the main
 * application which calls on the relevant APIs from the user and product services within
 * its service layer in order to be able to persist the data stored in the Aperture database
 * - this application has all of the view functionality which is handled within the controller,
 * as well as with the HTML, CSS and JavaScript files found within the resources folder to 
 * create a easy to use user interface on port 8086
 * IMPORTANT - BOTH SERVICES AND THIS APPLICATION MUST BE RUNNING FOR FULL WEB APP FUNCTIONALITY */
@SpringBootApplication(scanBasePackages = "com.emily")
@EnableJpaRepositories(basePackages = "com.emily.persistence")
public class ApertureHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApertureHomeApplication.class, args);
	}
	
	/* Creating a RestTemplate bean so that the application is able to consume RESTful web 
	 * services - this allows the application to call on the APIs from the services 
	 * by performing HTTP requests */
	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}

}
