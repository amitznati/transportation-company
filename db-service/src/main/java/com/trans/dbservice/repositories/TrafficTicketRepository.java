package com.trans.dbservice.repositories;

import javax.transaction.Transactional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.TrafficTicket;

@Transactional
@RepositoryRestResource(collectionResourceRel = "traffictickets", path = "traffictickets")
public interface TrafficTicketRepository extends EventBaseRepository<TrafficTicket> {

}
