package com.trans.dbservice.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("PT")
@JsonTypeName("parkingtickets")
public class ParkingTicket extends Event {

	private Double amountOfFine;
}
