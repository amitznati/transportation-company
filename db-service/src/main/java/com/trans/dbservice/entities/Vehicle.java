package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {
	
	public Vehicle(String license) {
		this.license = license;
	}
	@Id
	@GeneratedValue
	private String id;
	
	@Column(nullable = false,unique = true)
	private String license;
	
	@ManyToOne
    @JoinColumn(name="driver_id")
	private Driver driver;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Event> events;

}
