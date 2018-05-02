package com.trans.dbservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.Driver;

@RepositoryRestResource(collectionResourceRel = "drivers", path = "drivers")
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
