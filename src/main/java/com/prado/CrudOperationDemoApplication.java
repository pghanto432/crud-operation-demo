package com.prado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.prado.entity")
@EnableJpaRepositories(basePackages = "com.prado.repository")

public class CrudOperationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudOperationDemoApplication.class, args);
	}

}
