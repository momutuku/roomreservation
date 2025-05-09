package com.group1.roomreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// (exclude = ErrorMvcAutoConfiguration.class)
public class RoomreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomreservationApplication.class, args);
	}

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// application) {
	// return application.sources(RoomreservationApplication.class);
	// }

}
