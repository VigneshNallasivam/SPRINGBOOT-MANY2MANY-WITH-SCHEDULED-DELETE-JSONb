package com.spring.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Many2Application {

	public static void main(String[] args) {
		SpringApplication.run(Many2Application.class, args);
	}

}
