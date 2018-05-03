package com.trans.dbservice.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
@DiscriminatorValue("trafficticket")
@JsonTypeName("trafficticket")
public class TrafficTicket extends Event {

	private TrafficTicketCause cause;
}
