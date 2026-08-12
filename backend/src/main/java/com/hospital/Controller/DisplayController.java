package com.hospital.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.DtoRequest.CallTokenRequest;
import com.hospital.DtoRequest.CompleteTokenRequest;
import com.hospital.DtoResponse.DisplayTokenResponse;
import com.hospital.commom.ApiResponse;
import com.hospital.serviceImpl.DisplayService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/display")
@RequiredArgsConstructor
public class DisplayController {

	private final DisplayService displayService;

	// http://localhost:9090/token-system/api/display/current
		@GetMapping("/current")
		public ApiResponse<List<DisplayTokenResponse>> getCurrentDisplayToken(){
			return ApiResponse.<List<DisplayTokenResponse>>builder()
					.success(true)
					.message("currently called token fetch succesfully")
					.data(displayService.getCurrentlyCalledToken())
					.build();
		}
		
@PostMapping("/call-next")  //http://localhost:9090/token-syatem/api/display/call-next
	public ApiResponse<DisplayTokenResponse>  callNextToken(@Valid @RequestBody CallTokenRequest request){
		
		return ApiResponse.<DisplayTokenResponse>builder()
				.success(true)
				.message("NextToken Called sssfullyy")
				.data(displayService.callNextToken(request))
				.build();
	}

//http://localhost:9090/token-system/api/display/complete
	@PostMapping("/complete")
	public ApiResponse<DisplayTokenResponse> completeCurrentToken(@Valid @RequestBody CompleteTokenRequest request){
		return ApiResponse.<DisplayTokenResponse>builder()
				.success(true)
				.message("Token completed succesfully")
				.data(displayService.completeCurrentToken(request))
				.build();
	}
}
