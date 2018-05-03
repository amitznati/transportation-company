package com.trans.managerservice.controllers;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trans.managerservice.dto.DriverDTO;
import com.trans.managerservice.dto.EventDTO;
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

	@PostMapping("/trainings")
	public TrainingDTO addTraining(@RequestBody TrainingDTO training){
		log.info("Adding Training");
		TrainingDTO retVal = null;
		try {
			HttpEntity<TrainingDTO> request = new HttpEntity<>(training);
			retVal = restTemplate.postForObject(dbUrl + "trainings/", request, TrainingDTO.class);
			log.info("Training added successfully with id, "+retVal.getId().toString());
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;

	}
	
	@GetMapping("/trainings")
	public List<TrainingDTO> getTraining(){
		log.info("Adding Training");
		List<TrainingDTO> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + "trainings", 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<TrainingDTO>>() {})
					
					.getBody()
					.getContent()
					.stream()
					.collect(Collectors.toList());
			log.info("Retrieve all Training successfully");
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;

	}

	@GetMapping("/calculate-bonus")
	public HashMap<DriverDTO, Integer> calcBonus(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		log.info("Calculation Bonus");
		HashMap<DriverDTO,Integer> map = new HashMap<>();
		restTemplate.exchange(dbUrl + "events", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<EventDTO>>() {})
				.getBody().getContent().stream()
				.filter(e -> e.getCreatedAt().before(to) && e.getCreatedAt().after(from))
				.map(e -> {
					DriverDTO driver = getDriverByID(e.getId().toString());
					log.info("Driver is: " +driver.toString());
					int points = calcPointsByType(e.getType());
					log.info("points are : "+points);
					if(map.get(driver) == null)
						map.put(driver, 0);
					map.put(driver, map.get(driver) + points);
					return e;
				})
				.collect(Collectors.toList());
		restTemplate.exchange(dbUrl + "trainings", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<TrainingDTO>>() {})
				.getBody().getContent().stream()
				.filter(t -> t.getStartDateTime().before(to) && t.getStartDateTime().after(from))
				.map(e -> {
					DriverDTO driver = getDriverByID(e.getId().toString());
					log.info("Driver is: " +driver.toString());
					if(map.get(driver) == null)
						map.put(driver, 0);
					map.put(driver, map.get(driver) + 100);
					return e;
				})
				.collect(Collectors.toList());
		return map;

	}

	private int calcPointsByType(String type) {
		int points = 0;
		switch (type) {
		case "AC":
			points = -3;
			break;
		case "TT":
			points = -2;
			break;
		case "PT":
			points = -1;
			break;
		default:
			break;
		}
		return points*100;
	}
	
	private DriverDTO getDriverByID(String id) {
		return restTemplate.getForObject(dbUrl + "events/" + id+"/driver", DriverDTO.class);
	}
}
