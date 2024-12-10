package com.klef.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.klef.example.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class CareerGuidanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerGuidanceApplication.class, args);
	}

}
