package com.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.Services.StorageService;

@SpringBootApplication
public class DatCodeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatCodeSpringBootApplication.class, args);
		System.out.print("Run Success!!!");
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
	
}
