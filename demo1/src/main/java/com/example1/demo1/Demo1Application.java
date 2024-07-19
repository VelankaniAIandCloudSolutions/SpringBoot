package com.example1.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example1.demo1.repositories")
@EntityScan(basePackages = "com.example1.demo1.entities")
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
