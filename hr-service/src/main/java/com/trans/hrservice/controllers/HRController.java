package com.trans.hrservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HRController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/drivers/add")
	String addDriver(){
		log.info("HF Service register Event");
		return "ok";
	}
	
	@GetMapping("/managers/add")
	String addTraining(){
		log.info("HF Service register Event");
		restTemplate.getForEntity("http://quality-verifier/verify-beer-quality", String.class);
		return "ok";
	}
}
