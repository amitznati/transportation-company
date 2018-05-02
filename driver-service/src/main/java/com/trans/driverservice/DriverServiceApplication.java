package com.trans.driverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class DriverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriverServiceApplication.class, args);
	}
}

@Configuration
class Config{
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
