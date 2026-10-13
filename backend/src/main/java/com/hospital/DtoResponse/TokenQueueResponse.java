package com.hospital.DtoResponse;

import com.hospital.commom.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenQueueResponse {   

	private Long id;
	private Integer  tokenNumber;
	private String status;
	private String counterCode;
	private String displayName;
}
