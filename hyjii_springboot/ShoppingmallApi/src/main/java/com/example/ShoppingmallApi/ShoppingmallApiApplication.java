package com.example.ShoppingmallApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShoppingmallApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShoppingmallApiApplication.class, args);
	}
}