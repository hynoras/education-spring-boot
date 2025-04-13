package com.example.education_spring_boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EducationSpringBootApplication implements CommandLineRunner {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(EducationSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.getenv().forEach((key, val) -> {
			if (key.toLowerCase().contains("spring") || key.toLowerCase().contains("db")) {
				System.out.println("ENV: " + key + " = " + val);
			}
		});
	}
}
