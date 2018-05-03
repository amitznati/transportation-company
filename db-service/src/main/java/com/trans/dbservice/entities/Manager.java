package com.trans.dbservice.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
//@Data
@RequiredArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Manager extends Employee {

	public Manager(String name) {super(name);}
	
	@OneToMany(mappedBy = "openingManager")
	private List<Training> trainings;
}
