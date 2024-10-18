package com.xanaxna.Rest.API.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class RestApiExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiExampleApplication.class, args);
	}

}
