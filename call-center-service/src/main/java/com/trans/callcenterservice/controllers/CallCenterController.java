package com.trans.callcenterservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallCenterController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/register-event")
	String registerEvent(){
		log.info("HF Service register Event");
		restTemplate.getForEntity("http://quality-verifier/verify-beer-quality", String.class);
		return "ok";
	}

}
