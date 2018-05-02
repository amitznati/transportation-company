package com.trans.dbservice.entities;

import javax.persistence.Entity;

import com.trans.dbservice.entities.enums.AccidentCause;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TrafficTicket extends Event {

	private AccidentCause cause;
}
