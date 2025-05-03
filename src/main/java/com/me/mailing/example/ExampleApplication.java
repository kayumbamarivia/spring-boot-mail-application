package com.me.mailing.example;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

	private static final Logger logger = Logger.getLogger(ExampleApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
		logger.info("Welcome to http://localhost:8080");
		logger.info("You can test the mail service by sending a POST request to http://localhost:8080/api/v1/email/send");
	}

}
