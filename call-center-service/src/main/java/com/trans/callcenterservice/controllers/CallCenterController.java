package com.trans.callcenterservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallCenterController {
	
	@Autowired
	RestTemplate restTemplate;
	private final String dbUrl ="http://db-service/";
	
	@GetMapping("/events")
	List<ObjectNode> getAllEvent(){
		List<ObjectNode> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + "events", 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<ObjectNode>>() {})
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
	
	@GetMapping("/events/{type}")
	List<ObjectNode> getAllEventByType(@PathVariable String type){
		log.info("Adding Training");
		List<ObjectNode> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + type, 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<ObjectNode>>() {})
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
	
	
	
	@PostMapping("/events/{type}")
	public ObjectNode addParkingTicket(@RequestBody ObjectNode pt,@PathVariable String type,
			@RequestParam("driver_id") String driverId,
			@RequestParam("vehicle_id") String vehicleId){
		log.info("Adding parking ticket...");
		
		return addEvent(pt, driverId, vehicleId, type);
	}
	
	private ObjectNode addEvent(ObjectNode event,String driverId, String vehicleId, String type) {
		event.put("type",type);
		HttpEntity<ObjectNode> request = new HttpEntity<>(event);
		ObjectNode saveedEvent = null;
		try {
			ObjectNode retVal = restTemplate.postForObject(dbUrl + type, request, ObjectNode.class);
			updateEvent(retVal.get("id").toString(), driverId, vehicleId);
			saveedEvent = restTemplate.getForObject(dbUrl + type + "/" + retVal.get("id"), ObjectNode.class);
		} catch (Exception e) {
			throw e;
		}
		return saveedEvent;
	}
	
	public void updateEvent(String eventId,String driverId,String vehicleId){
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-type", "text/uri-list");
			HttpEntity<String> httpEntity = new HttpEntity<>(dbUrl + "driver/"+driverId+"\n" , requestHeaders);
			restTemplate.exchange(dbUrl + "events/"+eventId+"/driver", HttpMethod.PUT, httpEntity, String.class);

			httpEntity = new HttpEntity<>(dbUrl + "/vehicles/"+vehicleId+"\n" , requestHeaders);
			restTemplate.exchange(dbUrl + "/events/"+eventId+"/vehicle", HttpMethod.PUT, httpEntity, String.class);
			
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
	}
	
	

}
