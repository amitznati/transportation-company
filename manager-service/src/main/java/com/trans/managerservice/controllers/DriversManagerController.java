package com.trans.managerservice.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.trans.managerservice.dto.Driver;
import com.trans.managerservice.dto.Training;
import com.trans.managerservice.services.ManagerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DriversManagerController {
	
	@Autowired
	ManagerService managerService;
	
	
	@GetMapping("/drivers-names")
	public List<String> getDriversNames(){
		return managerService.getDriversNames();
	}

	@PostMapping("/trainings")
	public Training addTraining(@RequestBody Training training){
		return managerService.addTraining(training);
	}
	
	@GetMapping("/trainings")
	public List<Training> getTraining(){
		return managerService.getTraining();
	}

	@GetMapping("/calculate-bonus")
	public HashMap<Driver, Integer> calcBonus(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		HashMap<Driver,Integer> map = managerService.calcBonus(from, to);
		return map;
	}
	
	@GetMapping("/events-breackdown")
	public HashMap<String, Double> getEventsBreackdown(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		log.info("Rest events-breackdown started...");
		HashMap<String, Double> map = managerService.getEventsBreackdown(from, to);
		return map;
	}
	
	@GetMapping("/bonus-balance")
	public HashMap<String, Integer> getBonusBalance(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		log.info("Rest bonus-balance started...");
		HashMap<String, Integer> map = managerService.getBonusBalance(from, to);
		return map;
	}
}
