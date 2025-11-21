package com.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CBSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CBSApplication.class, args);
	}

}
