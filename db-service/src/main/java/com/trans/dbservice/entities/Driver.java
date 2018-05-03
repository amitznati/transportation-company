package com.trans.dbservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Driver extends Employee {

	public Driver(String name) {
		super(name);
	}
	
	@OneToMany(mappedBy = "driver")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "driver")
	@Setter
	private List<Event> events;
	
	@ManyToMany(mappedBy = "drivers")
	private List<Training> registeredToTrainings;
	
	
}
