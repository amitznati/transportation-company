package com.trans.callcenterservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
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
import com.trans.callcenterservice.dto.TrafficTicketDTO;
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
	
//	@PostMapping("/events/accidents")
//	public AccidentDTO addAccident(@RequestBody AccidentDTO event,
//			@RequestParam("driver_id") int driverId,
//			@RequestParam("vehicle_id") int vehicleId){
//		log.info("Adding Accident...");
//		event.setType("accidents");
//		HttpEntity<AccidentDTO> request = new HttpEntity<>(event);
//		AccidentDTO retVal = restTemplate.postForObject(dbUrl + "accidents", request, AccidentDTO.class);
//		updateEvent(retVal, driverId, vehicleId, "accidents");
//		AccidentDTO savedAccident = restTemplate.getForObject(dbUrl + "accidents/"+retVal.getId(), AccidentDTO.class);
//		return savedAccident;
//	}
	

	
//	public void updateEvent(EventDTO event,int driverId,int vehicleId,String type){
//		try {
//			//event.setType(type);
//			DriverDTO driver = restTemplate.getForObject(dbUrl + "drivers/"+driverId, DriverDTO.class);
//			VehicleDTO vehicle = restTemplate.getForObject(dbUrl + "vehicles/"+vehicleId, VehicleDTO.class);
//			//HttpEntity<EventDTO> request = new HttpEntity<>(event);
//			//retVal = restTemplate.postForObject(dbUrl + "events/" +event.getId(), request, EventDTO.class);
//			event.setType(type);
//			event.setDriver(driver);
//			event.setVehicle(vehicle);
//			restTemplate.put(dbUrl +type+"/" +event.getId(), event);
//			
//		}catch (Exception e) {
//			log.error("Failed to add training...");
//			log.error(e.getMessage());
//			throw e;
//		}
//	}

	@PostMapping("/events/accidents")
	public AccidentDTO addAccident(@RequestBody AccidentDTO event,
			@RequestParam("driver_id") int driverId,
			@RequestParam("vehicle_id") int vehicleId){
		log.info("Adding Accident...");
		event.setType("accidents");
		AccidentDTO retVal = null;
		try {
//			DriverDTO driver = restTemplate.getForObject(dbUrl + "drivers/"+driverId, DriverDTO.class);
//			VehicleDTO vehicle = restTemplate.getForObject(dbUrl + "vehicles/"+vehicleId, VehicleDTO.class);
//			event.setDriver(driver);
//			event.setVehicle(vehicle);
			HttpEntity<AccidentDTO> request = new HttpEntity<>(event);
			retVal = restTemplate.postForObject(dbUrl + "accidents", request, AccidentDTO.class);
//			retVal.setType("accidents");
//			retVal.setDriver(driver);
//			retVal.setVehicle(vehicle);
//			restTemplate.put(dbUrl + "accidents/"+retVal.getId(), retVal);
			
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;
	}
	
	@PostMapping("/events/trafficticket")
	public TrafficTicketDTO addTraffik(@RequestBody TrafficTicketDTO event,
			@RequestParam("driver_id") int driverId,
			@RequestParam("vehicle_id") int vehicleId){
		log.info("Adding Traffik...");
		event.setType("trafficTicket");
		TrafficTicketDTO retVal = null;
		try {
			DriverDTO driver = restTemplate.getForObject(dbUrl + "drivers/"+driverId, DriverDTO.class);
			VehicleDTO vehicle = restTemplate.getForObject(dbUrl + "vehicles/"+vehicleId, VehicleDTO.class);
			HttpEntity<TrafficTicketDTO> request = new HttpEntity<>(event);
			retVal = restTemplate.postForObject(dbUrl + "traffictickets", request, TrafficTicketDTO.class);
			retVal.setType("trafficTicket");
			retVal.setDriver(driver);
			retVal.setVehicle(vehicle);
			restTemplate.put(dbUrl + "accidents/"+retVal.getId(), retVal);
			
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;
	}

}
