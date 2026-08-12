package com.hospital.serviceImpl;

import java.util.List;

import com.hospital.DtoRequest.CallTokenRequest;
import com.hospital.DtoRequest.CompleteTokenRequest;
import com.hospital.DtoResponse.DisplayTokenResponse;

public interface DisplayService {

	DisplayTokenResponse callNextToken(CallTokenRequest request);
	DisplayTokenResponse completeCurrentToken(CompleteTokenRequest request);
 List<DisplayTokenResponse> getCurrentlyCalledToken();

}
