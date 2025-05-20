package com.tcs.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAccMovApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccMovApplication.class, args);
	}

}
