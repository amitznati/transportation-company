package com.trans.managerservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class ManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerServiceApplication.class, args);
		
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

@RestController
class DriversNamesController {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/drivers-names")
	public List<String> getDriversNames(){
		return restTemplate
									.exchange("http://db-service/drivers/", 
									HttpMethod.GET, null, 
									new ParameterizedTypeReference<Resources<DriverDTO>>() {})
									
									.getBody()
									.getContent()
									.stream()
									.map(DriverDTO::getName)
									.collect(Collectors.toList());

	}
}

@Data
@RequiredArgsConstructor
@AllArgsConstructor
class DriverDTO {
	private Long id;
	private String name;
}