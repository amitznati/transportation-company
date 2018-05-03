package com.trans.managerservice.controllers;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public URI addTraining(@RequestBody TrainingDTO training){
		log.info("Adding Training");
		TrainingDTO retVal = null;
		URI uri = null;
		try {
			HttpEntity<TrainingDTO> request = new HttpEntity<>(training);
			uri = restTemplate.postForLocation(dbUrl + "/trainings/",request);
			log.info(uri.toString());
			//URI uri = restTemplate.postForLocation(dbUrl + "/trainings/", training);
			//retVal = restTemplate.getForObject(dbUrl + "/trainings/", TrainingDTO.class);
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return uri;

	}
	
	@GetMapping("/calculate-bonus")
	public HashMap<DriverDTO, String> calcBonus(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		log.info("Calculation Bonus");
		HashMap<DriverDTO,String> map = new HashMap<>();
		//for each driver 
		// 	bonus = 100*((trainings.count * 1) +
		//			(accident.count * -3) +
		//          (paringT.count * -1) + 
		//			(trafficT.count * -2))
		// 	map.push(driver,bonus)
		//return map
		
		map.put(new DriverDTO(null,"sdfsdf",null), "200");
		map.put(new DriverDTO(null,"gfhfg",null), "-400");
		map.put(new DriverDTO(null,"gfhfgfhg",null), "-500");
		return map;

	}
}
