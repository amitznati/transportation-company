package com.trans.dbservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.trans.dbservice.entities.Training;

@RepositoryRestResource(collectionResourceRel = "trainings", path = "trainings")
public interface TrainingRepository extends JpaRepository<Training, Long> {

}
