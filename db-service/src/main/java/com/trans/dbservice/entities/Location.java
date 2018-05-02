package com.trans.dbservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Location {

	@Id
	@GeneratedValue
	private Long id;
	
	private String city;
	private String street;
	
	@OneToOne(mappedBy = "location")
	private Event event;
}
