package com.hazard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.hazard.model"} )
@ComponentScan
public class HazardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazardApplication.class, args);
	}

}
