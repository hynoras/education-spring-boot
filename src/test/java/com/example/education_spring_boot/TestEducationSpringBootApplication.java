package com.example.education_spring_boot;

import org.springframework.boot.SpringApplication;

public class TestEducationSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.from(EducationSpringBootApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
