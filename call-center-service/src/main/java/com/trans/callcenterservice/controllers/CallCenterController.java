package com.trans.callcenterservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trans.callcenterservice.dto.AccidentDTO;
import com.trans.callcenterservice.dto.DriverDTO;
import com.trans.callcenterservice.dto.EventDTO;
import com.trans.callcenterservice.dto.VehicleDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallCenterController {
	
	@Autowired
	RestTemplate restTemplate;
	private final String dbUrl ="http://db-service/";
	
	@GetMapping("/events")
	List<EventDTO> getAllEvent(){
		log.info("Adding Training");
		List<EventDTO> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + "events", 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<EventDTO>>() {})
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
	
	@GetMapping("/events/accidents")
	List<AccidentDTO> getAllAccident(){
		log.info("Adding Training");
		List<AccidentDTO> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + "accidents", 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<AccidentDTO>>() {})
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
	
	@PostMapping("/events/accidents")
	public AccidentDTO addTraining(@RequestBody AccidentDTO event,
			@RequestParam("driver_id") int driverId,
			@RequestParam("vehicle_id") int vehicleId){
		log.info("Adding Accident...");
		DriverDTO driver = restTemplate.getForObject(dbUrl + "drivers/"+driverId, DriverDTO.class);
		//event.setVehicle(restTemplate.getForObject(dbUrl + "vehicles/"+vehicleId, VehicleDTO.class));
		event.setType("accidents");
		AccidentDTO retVal = null;
		try {
			HttpEntity<AccidentDTO> request = new HttpEntity<>(event);
			retVal = restTemplate.postForObject(dbUrl + "accidents", request, AccidentDTO.class);
			//restTemplate.postForObject(dbUrl + "accidents/driver/"+driverId, new HttpEntity<>(driver),DriverDTO.class);
			restTemplate.put(dbUrl + "accidents/driver/"+driverId, DriverDTO.class);
			//restTemplate.postForLocation(dbUrl + "accidents/vehicle/"+vehicleId, VehicleDTO.class);
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;
	}

//	private Object saveEvent(EventDTO event,String type) {
//		Object retVal = null;
//		try {
//			HttpEntity<EventDTO> request = new HttpEntity<>(event);
//			retVal = restTemplate.postForObject(dbUrl + type, request, EventDTO.class);
//		}catch (Exception e) {
//			log.error("Failed to add training...");
//			log.error(e.getMessage());
//			throw e;
//		}
//		return retVal;
//	}

}
