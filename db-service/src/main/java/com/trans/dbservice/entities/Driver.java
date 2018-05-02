package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Driver extends Employee{

	
	@OneToMany(mappedBy = "driver")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "driver")
	private List<Event> events;
	
	@OneToMany(mappedBy = "otherDriver")
	private List<Accident> involveInAccidents;
	
	@ManyToMany(mappedBy = "drivers")
	private List<Training> registeredToTrainings;
}
