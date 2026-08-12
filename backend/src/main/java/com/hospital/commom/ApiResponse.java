package com.hospital.commom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {      //response is anythis 

	private boolean success;
	private String message;
	private T data;        //data is response
}
