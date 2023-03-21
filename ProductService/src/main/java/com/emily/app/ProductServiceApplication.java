package com.emily.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//scanning appropriate packages to wire up the service
@SpringBootApplication(scanBasePackages = "com.emily")
@EntityScan(basePackages = "com.emily.entity")
@EnableJpaRepositories(basePackages = "com.emily.persistence")
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
