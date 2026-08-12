package com.hospital.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.DtoResponse.TokenQueueResponse;
import com.hospital.entity.TokenQueue;
import com.hospital.enums.TokenStatus;
import com.hospital.repo.TokenQueueRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TokenQueueServiceImpl implements TokenQueueService{

	private final TokenQueueRepository tokenQueueRepository;
	@Override
	public TokenQueueResponse createToken() {

		Integer maxTokenNumber=tokenQueueRepository.findMaxTokenNumber();
		int nextTokenNumber=(maxTokenNumber==null)?1:maxTokenNumber+1;
		
		TokenQueue  tokenQueue=TokenQueue.builder()
				.tokenNumber(nextTokenNumber)
				.status(TokenStatus.WAITING)
//				.counter(null)   
//				.calleTime(null)
				.build();
		
		
		TokenQueue savedToken=tokenQueueRepository.save(tokenQueue);  //insert
		
		
		return TokenQueueResponse
				.builder()
				.id(savedToken.getId()).tokenNumber(savedToken.getTokenNumber())
				.status(savedToken.getStatus().name())
//				.counterCode(null)
//				.displayName(null)
				.build();
	}
	
	@Override
	public List<TokenQueueResponse> getWaitingToken() {
		List<TokenQueue> waitingTokens = tokenQueueRepository.findByStatus(TokenStatus.WAITING);

		return waitingTokens.stream()
				.map(token -> TokenQueueResponse.builder()
						.id(token.getId())
						.tokenNumber(token.getTokenNumber())
						.status(token.getStatus().name())
						.build())
				.toList();
	}

}
