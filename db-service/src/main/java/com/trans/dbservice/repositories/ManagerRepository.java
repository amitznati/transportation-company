package com.trans.dbservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.Manager;

@RepositoryRestResource(collectionResourceRel = "mamangers", path = "mamangers")
public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
