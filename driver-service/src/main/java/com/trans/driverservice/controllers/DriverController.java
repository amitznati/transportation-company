package com.trans.driverservice.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DriverController {

	@Autowired
	RestTemplate restTemplate;
	private String dbUrl ="http://db-service/";
	
	@PostMapping("/register-driver/${driver_id}/to-trainig/${training-id}")
	public String getDriversNames(){
		log.info("Driver-Service::Register driver to training...");
		return "ok";

	}
}
