package com.trans.managerservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trans.managerservice.dto.DriverDTO;

@RestController
public class DriversManagerController {
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
	
	@GetMapping("/trainings/add")
	public List<String> addTraining(){
		return null;

	}
	
	@GetMapping("/calculate-bonus/")
	public HashMap<DriverDTO, String> calcBonus(){
		return null;

	}
}
