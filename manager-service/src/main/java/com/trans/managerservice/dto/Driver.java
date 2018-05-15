package com.trans.managerservice.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Driver {
	private Long id;
	private String name;
	//private List<VehicleDTO> vehicles;
	private List<Event> events;
	private List<Training> registeredToTrainings;
	
}