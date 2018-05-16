package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
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
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@Setter
	@Getter
	@JoinTable(
	        name = "driver_training", 
	        joinColumns = @JoinColumn(name = "driver_id") , 
	        inverseJoinColumns = @JoinColumn(name = "training_id",
	        referencedColumnName = "id"))
	private List<Training> trainings;
	
	
}
