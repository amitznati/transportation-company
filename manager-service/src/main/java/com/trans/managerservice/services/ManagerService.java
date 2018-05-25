package com.trans.managerservice.services;

import java.text.DecimalFormat;
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
	static
	RestTemplate restTemplate;
	private static final String dbUrl ="http://db-service/";
	
	public static HashMap<Driver,Integer> calcBonus(Date from, Date to){
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
	
	private static int calcPointsByType(String type) {
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
	
	public static HashMap<String, String> getEventsBreackdown(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		DecimalFormat df2 = new DecimalFormat(".##");
		int sumAll = restTemplate.exchange(dbUrl + "events", 
				HttpMethod.GET, null, 
				new ParameterizedTypeReference<Resources<Event>>() {})
				.getBody().getContent().stream()
				.map(e -> {
					if(map.get(e.getType()) == null)
						map.put(e.getType(), 0);
					map.put(e.getType(),map.get(e.getType()) + 1);
					return e;
				}).collect(Collectors.toList()).size();

		log.info("Map: "+map);
		log.info("Sumall: " +sumAll);
		HashMap<String, String> retMap = new HashMap<String, String>();
		map.forEach( (k,v) -> {
			retMap.put(k,df2.format(Double.valueOf(v*100)/sumAll));
		});
		return retMap;
	}
	
	public static List<Training> getTraining(){
		log.info("Adding Training");
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
	
	public static Training addTraining(Training training){
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
	
	public static List<String> getDriversNames(){
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
