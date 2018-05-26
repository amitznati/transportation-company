package com.trans.managerservice.services;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.trans.managerservice.dto.Driver;
import com.trans.managerservice.dto.Event;
import com.trans.managerservice.dto.Training;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerService {

	private static final String ACCIDENT = "AC";
	private static final String TRAFFICK_TICKET = "TT";
	private static final String PARKING_TICKET = "PT";
	@Autowired
	RestTemplate restTemplate;
	private  final String dbUrl ="http://db-service/";
	
	public  HashMap<Driver,Integer> calcBonus(Date from, Date to){
		log.info("Calculation Bonus");
		HashMap<Driver,Integer> map = new HashMap<>();
		restTemplate.exchange(dbUrl + "events", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<Event>>() {})
				.getBody().getContent().stream()
				.filter(e -> e.getCreatedAt().before(to) && e.getCreatedAt().after(from))
				.map(e -> {
					Driver driver = restTemplate.getForObject(dbUrl+"events/"+e.getId()+"/driver", Driver.class);
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
				new ParameterizedTypeReference<Resources<Training>>() {})
				.getBody().getContent().stream()
				.filter(t -> t.getStartDateTime().before(to) && t.getStartDateTime().after(from))
				.map(t -> {
					t.getDrivers().forEach(driver -> {
						log.info("Driver is: " +driver.toString());
						if(map.get(driver) == null)
							map.put(driver, 0);
						map.put(driver, map.get(driver) + 100);
					});
					return t;
				})
				.collect(Collectors.toList());
		return map;

	}
	
	private  int calcPointsByType(String type) {
		int points = 0;
		switch (type) {
		case ACCIDENT:
			points = -3;
			break;
		case TRAFFICK_TICKET:
			points = -2;
			break;
		case PARKING_TICKET:
			points = -1;
			break;
		default:
			break;
		}
		return points*100;
	}
	
	public HashMap<String, Integer> getBonusBalance(Date from, Date to){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Arrays.asList("TR","AC","TT","PT").forEach( v -> map.put(v, 0));
		restTemplate.exchange(dbUrl + "events", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<Event>>() {})
				.getBody().getContent().stream()
				.filter(e -> e.getCreatedAt().before(to) && e.getCreatedAt().after(from))
				.map(e -> {
					map.put(e.getType(),map.get(e.getType()) + calcPointsByType(e.getType()));
					return e;
				}).collect(Collectors.toList());
		restTemplate.exchange(dbUrl + "trainings", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<Training>>() {})
				.getBody().getContent().stream()
				.filter(t -> t.getStartDateTime().before(to) && t.getStartDateTime().after(from))
				.map(t -> {
						map.put("TR", map.get("TR") + 100);
					return t;
				})
				.collect(Collectors.toList());
		return map;
	}
	
	public  HashMap<String, Double> getEventsBreackdown(Date from, Date to){
		HashMap<String, Double> map = new HashMap<String, Double>();
//		DecimalFormat df2 = new DecimalFormat(".##");
		int sumAll = restTemplate.exchange(dbUrl + "events", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<Event>>() {})
				.getBody().getContent().stream()
				.filter(e -> e.getCreatedAt().before(to) && e.getCreatedAt().after(from))
				.map(e -> {
					if(map.get(e.getType()) == null)
						map.put(e.getType(), 0.00);
					map.put(e.getType(),map.get(e.getType()) + 1);
					return e;
				}).collect(Collectors.toList()).size();
		map.replaceAll( (k,v) -> v * 100.00/sumAll);
		return map;
	}
	
	public  List<Training> getTraining(){
		List<Training> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + "trainings", 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<Training>>() {})
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
	
	public  Training addTraining(Training training){
		log.info("Adding Training");
		Training retVal = null;
		try {
			HttpEntity<Training> request = new HttpEntity<>(training);
			retVal = restTemplate.postForObject(dbUrl + "trainings/", request, Training.class);
			log.info("Training added successfully with id, "+retVal.getId().toString());
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;

	}
	
	public  List<String> getDriversNames(){
		return restTemplate.exchange(dbUrl + "drivers/", 
									HttpMethod.GET, null, 
									new ParameterizedTypeReference<Resources<Driver>>() {})
									
									.getBody()
									.getContent()
									.stream()
									.map(Driver::getName)
									.collect(Collectors.toList());

	}
}
