package com.hazard.Hazard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.hazard.model"} )
public class HazardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazardApplication.class, args);
	}

}
