package com.gmail.RoadToJob2019.controllermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.gmail.RoadToJob2019.repositorymodule.models")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
