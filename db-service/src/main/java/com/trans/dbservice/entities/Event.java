package com.trans.dbservice.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Event {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
    @JoinColumn(name="driver_id")
	private Driver driver;
	
	@ManyToOne
    @JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id")
	private Location location;
	
	private String description;
	
}
