package com.gmail.roadtojob2019.controllermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.gmail.roadtojob2019.repositorymodule.models")
@ComponentScan(basePackages = {"com.gmail.roadtojob2019.repositorymodule", "com.gmail.roadtojob2019.servicemodule", "com.gmail.roadtojob2019.controllermodule"})
@EnableJpaRepositories("com.gmail.roadtojob2019.repositorymodule")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
