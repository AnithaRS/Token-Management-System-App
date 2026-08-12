package com.hospital.DtoResponse;

import com.hospital.commom.ApiResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisplayTokenResponse {

	private Integer tokenNumber;
	private String counterCode;
	private String counterName;
	private String displayName;
	private String status;
	
	
}
