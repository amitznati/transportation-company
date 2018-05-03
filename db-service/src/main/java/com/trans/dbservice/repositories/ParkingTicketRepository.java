package com.trans.dbservice.repositories;

import javax.transaction.Transactional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.ParkingTicket;

@Transactional
@RepositoryRestResource(collectionResourceRel = "parkingtickets", path = "parkingtickets")
public interface ParkingTicketRepository extends EventBaseRepository<ParkingTicket> {

}
