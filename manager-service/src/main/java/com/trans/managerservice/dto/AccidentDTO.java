package com.trans.managerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AccidentDTO extends EventDTO {
	
	private String otherDriverName;
	private String otherDriverPhone;
	private String otherDriverLicense;
	private String otherVehicleLicense;

}
