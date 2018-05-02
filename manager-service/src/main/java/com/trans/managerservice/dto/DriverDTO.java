package com.trans.managerservice.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverDTO {
	private Long id;
	private String name;
	private List<VehicleTDO> vehicles;
	
}