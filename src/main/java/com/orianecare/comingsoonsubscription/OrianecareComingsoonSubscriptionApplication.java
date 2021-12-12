package com.orianecare.comingsoonsubscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrianecareComingsoonSubscriptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrianecareComingsoonSubscriptionApplication.class, args);
	}

}
