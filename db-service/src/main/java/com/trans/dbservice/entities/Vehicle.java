package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String type;
	
	@OneToOne(mappedBy = "vehicle")
	private Driver driver;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Event> events;
	
	@OneToMany(mappedBy = "otherVehicle")
	private List<Accident> involveInAccidents;

}
