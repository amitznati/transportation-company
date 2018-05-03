package com.trans.dbservice.repositories;

import javax.transaction.Transactional;

import com.trans.dbservice.entities.Accident;

@Transactional
public interface AccidentRepository extends EventBaseRepository<Accident> {

}
