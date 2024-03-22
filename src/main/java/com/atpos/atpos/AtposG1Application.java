package com.atpos.atpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AtposG1Application {

	public static void main(String[] args) {
		SpringApplication.run(AtposG1Application.class, args);
	}

}
