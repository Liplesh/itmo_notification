package ru.lipnin.notifyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NotifyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyserviceApplication.class, args);
	}

}
