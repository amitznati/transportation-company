package com.trans.dbservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startDateTime;
	
	@ManyToOne
    @JoinColumn(name="mamanger_id")
	private Manager openingManager;
	
	private int maxNumOfParticipants;
	
	@ManyToMany(mappedBy = "trainings")
	private List<Driver> drivers;
	
	@Transient
	public List<Driver> getDrivers(){return this.drivers;}
	
}
