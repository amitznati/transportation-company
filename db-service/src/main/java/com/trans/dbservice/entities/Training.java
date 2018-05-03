package com.trans.dbservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	@Column(length=1000)
	private String description;
	
	@Column(length = 15)
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	@ManyToOne
    @JoinColumn(name="mamanger_id")
	private Manager openingManager;
	
	private int maxNumOfParticipants;
	
	@ManyToMany(cascade = CascadeType.ALL )
//    @JoinTable(
//        name = "Driver_Training", 
//        joinColumns = { @JoinColumn(name = "driver_id") }, 
//        inverseJoinColumns = { @JoinColumn(name = "training_id") }
//    )
	private List<Driver> drivers;
	
}
