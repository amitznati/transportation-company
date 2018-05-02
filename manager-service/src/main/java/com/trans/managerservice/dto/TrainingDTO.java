package com.trans.managerservice.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TrainingDTO {
	
	private Long id;
	private String description;
	private String title;
	private Date startDateTime;
	
	private int maxNumOfParticipants;
	private List<DriverDTO> drivers;
}
