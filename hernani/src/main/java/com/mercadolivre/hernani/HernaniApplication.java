package com.mercadolivre.hernani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HernaniApplication {

	public static void main(String[] args) {
		SpringApplication.run(HernaniApplication.class, args);
	}

}
