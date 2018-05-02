package com.trans.dbservice.entities;

import javax.persistence.Column;
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
	
	@Column(length = 15)
	private String city;
	
	@Column(length = 15)
	private String street;
	
	@OneToOne(mappedBy = "location")
	private Event event;
}
