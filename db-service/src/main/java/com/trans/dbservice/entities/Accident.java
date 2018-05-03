package com.trans.dbservice.entities;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@DiscriminatorValue("accidents")
@JsonTypeName("accidents")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Accident extends Event {

	private String otherDriverName;
	private String otherDriverPhone;
	private String otherDriverLicense;
	private String otherVehicleLicense;
	
	
}
