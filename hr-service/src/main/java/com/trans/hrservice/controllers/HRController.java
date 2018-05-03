package com.trans.hrservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HRController {

	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping("/drivers/add")
	String addDriver(){
		log.info("HF adding new driver...");
		return "ok";
	}
	
	
	@PostMapping("/managers/add")
	String addTraining(){
		log.info("HR adding new manager...");
		restTemplate.getForEntity("http://quality-verifier/verify-beer-quality", String.class);
		return "ok";
	}
}
