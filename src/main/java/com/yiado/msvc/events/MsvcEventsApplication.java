package com.yiado.msvc.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcEventsApplication.class, args);
	}

}
