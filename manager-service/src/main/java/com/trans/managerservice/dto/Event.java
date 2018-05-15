package com.trans.managerservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Event {

	private Long id;
	private String type;
	private Driver driver;
	//private VehicleDTO vehicle;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date createdAt;
//	private Location location;
	
	private String description;
}
