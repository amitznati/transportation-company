package com.trans.dbservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineData", types=Driver.class)
public interface DriverProtection {

	Long getId();
	String getName();
}
