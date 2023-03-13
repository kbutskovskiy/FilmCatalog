package com.example.catalogfilm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CatalogFilmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogFilmApplication.class, args);
	}
}
