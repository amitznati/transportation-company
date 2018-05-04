package com.trans.callcenterservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = AccidentDTO.class, name = "AC"),
//    @JsonSubTypes.Type(value = TrafficTicketDTO.class, name = "TT"),
//    @JsonSubTypes.Type(value = ParkingTicketDTO.class, name = "PP")
//})
public class EventDTO {

	private Long id;
	private String type;
	private DriverDTO driver;
	private VehicleDTO vehicle;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date createdAt;
	private Location location;
	
	private String description;
}
