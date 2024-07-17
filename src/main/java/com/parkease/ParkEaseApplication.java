package com.parkease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ParkEaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkEaseApplication.class, args);
	}

}
