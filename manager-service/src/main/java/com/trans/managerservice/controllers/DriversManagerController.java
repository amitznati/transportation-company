package com.trans.managerservice.controllers;

import java.net.URI;
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
import com.trans.managerservice.dto.TrainingDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DriversManagerController {
	@Autowired
	RestTemplate restTemplate;
	private final String dbUrl ="http://db-service/";
	
	@GetMapping("/drivers-names")
	public List<String> getDriversNames(){
		return restTemplate.exchange(dbUrl + "drivers/", 
									HttpMethod.GET, null, 
									new ParameterizedTypeReference<Resources<DriverDTO>>() {})
									
									.getBody()
									.getContent()
									.stream()
									.map(DriverDTO::getName)
									.collect(Collectors.toList());

	}

	@PostMapping("/trainings/add")
	public TrainingDTO addTraining(@RequestBody TrainingDTO training){
		log.info("Adding Training");
		TrainingDTO retVal = null;
		try {
			HttpEntity<TrainingDTO> request = new HttpEntity<>(training);
			retVal = restTemplate.postForObject(dbUrl + "/trainings/",request,TrainingDTO.class);
			//URI uri = restTemplate.postForLocation(dbUrl + "/trainings/", training);
			//retVal = restTemplate.getForObject(dbUrl + "/trainings/", TrainingDTO.class);
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;

	}
	
	@GetMapping("/calculate-bonus/from/${from}/to/${to}")
	public HashMap<DriverDTO, String> calcBonus(){
		log.info("Calculation Bonus");
		//for each driver 
		// 	bonus = 100*((trainings.count * 1) +
		//			(accident.count * -3) +
		//          (paringT.count * -1) + 
		//			(trafficT.count * -2))
		// 	map.push(driver,bonus)
		//return map
		return null;

	}
}
