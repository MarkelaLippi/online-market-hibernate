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
/*
	https://www.boraji.com/spring-security-5-custom-userdetailsservice-example
	https://ru.wikibooks.org/wiki/Spring_Security/%D0%A2%D0%B5%D1%85%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%D0%BE%D0%B1%D0%B7%D0%BE%D1%80_Spring_Security
	https://habr.com/ru/post/203318/
	https://www.baeldung.com/get-user-in-spring-security
*/
}
