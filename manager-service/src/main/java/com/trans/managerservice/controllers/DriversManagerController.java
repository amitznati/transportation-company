package com.trans.managerservice.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	
	
	@GetMapping("/drivers-names")
	public List<String> getDriversNames(){
		return ManagerService.getDriversNames();
	}

	@PostMapping("/trainings")
	public Training addTraining(@RequestBody Training training){
		return ManagerService.addTraining(training);
	}
	
	@GetMapping("/trainings")
	public List<Training> getTraining(){
		return ManagerService.getTraining();
	}

	@GetMapping("/calculate-bonus")
	public HashMap<Driver, Integer> calcBonus(
			@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date from, 
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date to){
		HashMap<Driver,Integer> map = ManagerService.calcBonus(from, to);
		return map;
	}
	
	@GetMapping("/events-breackdown")
	public HashMap<String, String> getEventsBreackdown(){
		log.info("Rest events-breackdown started...");
		HashMap<String, String> map = ManagerService.getEventsBreackdown();
		return map;
	}
}
