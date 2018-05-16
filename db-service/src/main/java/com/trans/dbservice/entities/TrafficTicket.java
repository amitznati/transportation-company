package com.trans.dbservice.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.trans.dbservice.entities.enums.TrafficTicketCause;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@DiscriminatorValue("TT")
@JsonTypeName("traffictickets")
public class TrafficTicket extends Event {

	@Enumerated(EnumType.STRING)
	private TrafficTicketCause cause;
}
