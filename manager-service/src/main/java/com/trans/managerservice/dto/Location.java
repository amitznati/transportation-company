package com.trans.managerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Location {

	private Long id;
	private String city;
	private String street;
	private EventDTO event;

}
