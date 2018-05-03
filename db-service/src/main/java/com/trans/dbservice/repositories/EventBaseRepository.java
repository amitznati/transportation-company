package com.trans.dbservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.trans.dbservice.entities.Event;

@NoRepositoryBean
public interface EventBaseRepository<T extends Event> extends JpaRepository<T, Long> {

}
