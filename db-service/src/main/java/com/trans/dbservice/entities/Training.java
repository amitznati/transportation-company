package com.trans.dbservice.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	private int maxNumOfParticipants;
}
