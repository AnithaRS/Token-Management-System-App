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
public class TokenQueueResponse {   // patcent yathanavathu token nu sollra response calss

	private Long id;
	private Integer  tokenNumber;
	private String status;
	private String counterCode;
	private String displayName;
}
