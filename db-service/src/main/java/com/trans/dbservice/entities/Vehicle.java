package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Required;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vehicle {
	
//	public Vehicle(String license,Driver driver) {
//		this.license = license;
//		this.driver = driver;
//	}
	public Vehicle(String license) {
		this.license = license;
	}
	@Id
	@GeneratedValue
	private String id;
	
	@Column(nullable = false)
	private String license;
	
	@ManyToOne
    @JoinColumn(name="driver_id")
	private Driver driver;
	
	@OneToMany(mappedBy = "vehicle")
	private List<Event> events;

}
