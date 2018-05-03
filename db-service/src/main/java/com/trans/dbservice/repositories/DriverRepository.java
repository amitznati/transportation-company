package com.trans.dbservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.Driver;
import com.trans.dbservice.entities.DriverProtection;

@RepositoryRestResource(collectionResourceRel = "drivers", path = "drivers",excerptProjection = DriverProtection.class)
public interface DriverRepository extends JpaRepository<Driver, Long> {

}


