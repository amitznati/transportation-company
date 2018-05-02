package com.trans.dbservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 15)
	private String name;
}
