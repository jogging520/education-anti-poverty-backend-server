package com.northbrain.privilige;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.northbrain")
public class PriviligeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriviligeApplication.class, args);
	}
}
