package com.hospital.serviceImpl;

import java.util.List;

import com.hospital.DtoResponse.TokenQueueResponse;

public interface TokenQueueService {

    TokenQueueResponse createToken();
    List<TokenQueueResponse> getWaitingToken();   
}
