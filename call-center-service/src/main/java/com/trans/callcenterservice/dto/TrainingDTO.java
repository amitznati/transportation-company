package com.trans.callcenterservice.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startDateTime;
	
	private int maxNumOfParticipants;
	private List<DriverDTO> drivers;
}
