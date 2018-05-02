package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
	
	@OneToMany(mappedBy = "driver")
	private List<Event> events;
	
	@OneToMany(mappedBy = "otherDriver")
	private List<Accident> involveInAccidents;
}
