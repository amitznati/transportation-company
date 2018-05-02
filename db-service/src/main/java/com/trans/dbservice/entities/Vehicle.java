package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {
	
	@Id
	private String id;
	
	private String type;
	
	@ManyToOne
    @JoinColumn(name="driver_id")
	private Driver driver;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Event> events;
	
	@OneToMany(mappedBy = "otherVehicle")
	private List<Accident> involveInAccidents;

}
