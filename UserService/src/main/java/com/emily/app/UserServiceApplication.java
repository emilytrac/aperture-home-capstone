package com.emily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/* scanning appropriate packages to auto configure the service - this application 
 * is referred to as a service as its main function is to interact with data
 * stored in the user table of the MySQL database, the APIs created in the resource 
 * allow the main application (ApertureHome) to call on them in order to persist
 * the relevant data - this service has no view functionality as that is all left to
 * the main application */
@SpringBootApplication(scanBasePackages = "com.emily")
@EntityScan(basePackages = "com.emily.entity")
@EnableJpaRepositories(basePackages = "com.emily.persistence")
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}