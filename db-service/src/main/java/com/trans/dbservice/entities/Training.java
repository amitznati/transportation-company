package com.trans.dbservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Training {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	
	private String titel;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	private int maxNumOfParticipants;
	
	@ManyToMany(cascade = CascadeType.ALL )
//    @JoinTable(
//        name = "Driver_Training", 
//        joinColumns = { @JoinColumn(name = "driver_id") }, 
//        inverseJoinColumns = { @JoinColumn(name = "training_id") }
//    )
	private List<Driver> drivers;
	
}
