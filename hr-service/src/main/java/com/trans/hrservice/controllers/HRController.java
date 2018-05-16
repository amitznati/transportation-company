package com.trans.hrservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.hateoas.Resources;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HRController {

	@Autowired
	RestTemplate restTemplate;
	
	private final String dbUrl ="http://db-service/";
	
	@GetMapping("/employees/{type}")
	public List<ObjectNode> getAllEmployees(@PathVariable String type){
		log.info("Retrieve Employees");
		List<ObjectNode> retVal = null;
		try {
			retVal = restTemplate.exchange(dbUrl + type, 
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<Resources<ObjectNode>>() {})
					.getBody()
					.getContent()
					.stream()
					.collect(Collectors.toList());
			log.info("Retrieve all Employees successfully");
		}catch (Exception e) {
			log.error("Failed to add training...");
			log.error(e.getMessage());
			throw e;
		}
		return retVal;

	}
	
	@PostMapping("/employees/{type}")
	public ObjectNode addParkingTicket(@RequestBody ObjectNode emp,@PathVariable String type){
		log.info("Adding Employee type: "+type);
		HttpEntity<ObjectNode> request = new HttpEntity<>(emp);
		ObjectNode saveedEmployee = null;
		try {
			saveedEmployee = restTemplate.postForObject(dbUrl + type, request, ObjectNode.class);
		} catch (Exception e) {
			throw e;
		}
		return saveedEmployee;
	}
}
