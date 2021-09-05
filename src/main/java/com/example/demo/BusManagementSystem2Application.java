package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Security.SecurityConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demo" })
@Import(value = { SecurityConfiguration.class })

public class BusManagementSystem2Application {

	public static void main(String[] args) {
		SpringApplication.run(BusManagementSystem2Application.class, args);
	}

}
