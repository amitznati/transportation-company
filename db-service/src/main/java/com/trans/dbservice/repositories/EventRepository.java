package com.trans.dbservice.repositories;

import javax.transaction.Transactional;

import com.trans.dbservice.entities.Event;

@Transactional
public interface EventRepository extends EventBaseRepository<Event> {

}
