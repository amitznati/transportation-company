package com.trans.callcenterservice.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverDTO {
	private Long id;
	private String name;
	private List<VehicleDTO> vehicles;
	private List<EventDTO> events;
	private List<TrainingDTO> registeredToTrainings;
	
}