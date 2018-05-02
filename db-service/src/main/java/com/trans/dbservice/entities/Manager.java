package com.trans.dbservice.entities;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Manager extends Employee {

}
