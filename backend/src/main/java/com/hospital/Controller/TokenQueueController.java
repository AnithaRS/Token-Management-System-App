package com.hospital.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.DtoResponse.TokenQueueResponse;
import com.hospital.commom.ApiResponse;
import com.hospital.serviceImpl.TokenQueueServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenQueueController  {

	private final TokenQueueServiceImpl  tokenQueueServiceImpl;
	
	
	//http://localhost:9090 /token-system/api/tokens
	@PostMapping
	public ApiResponse<TokenQueueResponse> createToken(){
		return ApiResponse.<TokenQueueResponse>builder()
				.success(true)
				.message("Token created sussfullly")
				.data(tokenQueueServiceImpl.createToken())
				.build();
	}
	
	@GetMapping("/waiting")
	public ApiResponse<List<TokenQueueResponse>> getWaitingToken(){
		return ApiResponse.<List<TokenQueueResponse>>builder().success(true)
				.message("waiting token fetch sussfully").data(tokenQueueServiceImpl.getWaitingToken()).build();
	}
}
