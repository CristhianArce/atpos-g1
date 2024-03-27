package com.atpos.atpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AtposG1Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AtposG1Application.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(new LoggingInterceptor());
	}

}
