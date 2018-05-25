package com.trans.managerservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.trans.managerservice.services.ManagerService;

@Configuration
class Config{
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();

	}
	
	@Bean
	public ManagerService managerService() {
		return new ManagerService();
	}

}
