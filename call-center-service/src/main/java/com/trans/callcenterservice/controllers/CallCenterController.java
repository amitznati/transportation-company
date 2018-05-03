package com.trans.callcenterservice.controllers;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.trans.callcenterservice.dto.EventDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallCenterController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/events/add")
	String registerEvent(@RequestBody EventDTO event){
		log.info("HF Service register Event");
		Client client = Client.create();
		WebResource resource = client.resource("http://db-service/events");
		EventDTO retVal = resource.type(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.post(new GenericType<EventDTO>() {}, event);
		
		//restTemplate.getForEntity("http://quality-verifier/verify-beer-quality", String.class);
		return "ok";
	}

}
