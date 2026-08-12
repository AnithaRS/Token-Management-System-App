package com.hospital.DtoRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CallTokenRequest {
	@NotBlank(message = "counter code is required")
	private String counterCode;
}
