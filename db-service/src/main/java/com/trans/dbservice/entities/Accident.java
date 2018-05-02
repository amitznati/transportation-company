package com.trans.dbservice.entities;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Accident extends Event {

	@ManyToOne
	@JoinColumn(name = "other_driver_id")
	private Driver otherDriver;
	
	@ManyToOne
	@JoinColumn(name = "other_vehicle_id")
	private Vehicle otherVehicle;
	
	
}
