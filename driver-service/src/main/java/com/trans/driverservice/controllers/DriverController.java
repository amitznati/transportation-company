package com.trans.driverservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DriverController {

	@Autowired
	RestTemplate restTemplate;
	private String urlD ="http://db-service/drivers/";
	private String urlT ="http://db-service/trainings/";
	
	@PostMapping("/register-driver-to-training")
	public String getDriversNames(@RequestParam("driver_id") String driverId, 
			@RequestParam("training_id") String trainingId ) throws Exception{
		log.info("Driver-Service::Register driver to training...");
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(urlT + "/"+trainingId+"\n" , requestHeaders);
		ResponseEntity<String> out = restTemplate.exchange(urlD + "/"+driverId+"/trainings", 
		HttpMethod.PUT, httpEntity, String.class);
		
		return out.getStatusCode().toString();

	}
}
