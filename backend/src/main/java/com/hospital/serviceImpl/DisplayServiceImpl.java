package com.hospital.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.DtoRequest.CallTokenRequest;
import com.hospital.DtoRequest.CompleteTokenRequest;
import com.hospital.DtoResponse.DisplayTokenResponse;
import com.hospital.Exception.ResourseNotFoundException;
import com.hospital.Mapper.DisplayMapper;
import com.hospital.entity.TokenCounter;
import com.hospital.entity.TokenQueue;
import com.hospital.enums.TokenStatus;
import com.hospital.repo.TokenCounterRepostory;
import com.hospital.repo.TokenQueueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

	private final TokenQueueRepository tokenQueueRepository;
	private final TokenCounterRepostory tokenCounterRepostory;
	private final DisplayMapper displayMapper;

	@Override
	public List<DisplayTokenResponse> getCurrentlyCalledToken(){
		List<TokenQueue> calledToken=tokenQueueRepository.findByStatus(TokenStatus.CALLED);
		return calledToken.stream().map(displayMapper::toDisplayTokenResponse).toList();
	}
 	
	
//	@Override
//	@Transactional
//	public DisplayTokenResponse callNextToken(CallTokenRequest request) {
//		TokenCounter counter = tokenCounterRepostory.findByCounterCode(request.getCounterCode())
//				.orElseThrow(() -> new ResourseNotFoundException("Counter is notfound: " + request.getCounterCode()));
//
////		if (!counter.isActive()) {
////			throw new ResourseNotFoundException("counter is inactive: " + request.getCounterCode());
////		}
//		
//		if(Boolean.FALSE.equals(counter.isActive())) {
//			throw new ResourseNotFoundException("counter is inactive :"+request.getCounterCode());
//		}
//
//		TokenQueue nextWaitingToken = tokenQueueRepository
//				.findFirstByStatusOrderByTokenNumberAsc(TokenStatus.WAITING)
//				.orElseThrow(() -> new ResourseNotFoundException("no waiting tokwns are available"));
//
//		nextWaitingToken.setCounter(counter);
//		nextWaitingToken.setStatus(TokenStatus.CALLED);
//		nextWaitingToken.setCalleTime(LocalDateTime.now());
//		nextWaitingToken.setUpdatedAt(LocalDateTime.now());
//
//		TokenQueue savedToken = tokenQueueRepository.save(nextWaitingToken);
//
//		return DisplayTokenResponse.builder()
//				.tokenNumber(savedToken.getTokenNumber())
//				.counterCode(savedToken.getCounter() != null ? savedToken.getCounter().getCounterCode() : null)
//				.displayName(savedToken.getCounter() != null ? savedToken.getCounter().getDisplayName() : null)
//				.status(savedToken.getStatus() != null ? savedToken.getStatus().name() : null)
//				.build();
//		
////		return displayMapper.toDisplayTokenResponse(savedToken);
//	}
	
	
	@Override
	@Transactional
	public DisplayTokenResponse callNextToken(CallTokenRequest request) {
		TokenCounter counter = tokenCounterRepostory.findByCounterCode(request.getCounterCode())
				.orElseThrow(() -> new ResourseNotFoundException("Counter is notfound: " + request.getCounterCode()));

		if (Boolean.FALSE.equals(counter.isActive())) {
			throw new ResourseNotFoundException("counter is inactive :" + request.getCounterCode());
		}

		boolean counterBusy = tokenQueueRepository
				.findFirstByCounter_counterCodeAndStatus(request.getCounterCode(), TokenStatus.CALLED)
				.isPresent();

		if (counterBusy) {
			throw new ResourseNotFoundException(
					"Counter " + request.getCounterCode() + " is already serving a token. Please complete the current token before calling the next one.");
		}

		TokenQueue nextWaitingToken = tokenQueueRepository
				.findFirstByStatusOrderByTokenNumberAsc(TokenStatus.WAITING)
				.orElseThrow(() -> new ResourseNotFoundException("no waiting tokwns are available"));

		nextWaitingToken.setCounter(counter);
		nextWaitingToken.setStatus(TokenStatus.CALLED);
		nextWaitingToken.setCalleTime(LocalDateTime.now());
		nextWaitingToken.setUpdatedAt(LocalDateTime.now());

		TokenQueue savedToken = tokenQueueRepository.save(nextWaitingToken);

		return DisplayTokenResponse.builder()
				.tokenNumber(savedToken.getTokenNumber())
				.counterCode(savedToken.getCounter() != null ? savedToken.getCounter().getCounterCode() : null)
				.displayName(savedToken.getCounter() != null ? savedToken.getCounter().getDisplayName() : null)
				.status(savedToken.getStatus() != null ? savedToken.getStatus().name() : null)
				.build();
	}
	

	@Override
	@Transactional
	public DisplayTokenResponse completeCurrentToken(CompleteTokenRequest request) {
		TokenCounter counter = tokenCounterRepostory.findByCounterCode(request.getCounterCode())
				.orElseThrow(() -> new ResourseNotFoundException("Counter not found: " + request.getCounterCode()));

		TokenQueue currentToken = tokenQueueRepository
				.findFirstByCounter_counterCodeAndStatus(request.getCounterCode(), TokenStatus.CALLED)
				.orElseThrow(() -> new ResourseNotFoundException("No called token found for counter: " + request.getCounterCode()));

		currentToken.setStatus(TokenStatus.COMPLETED);
		currentToken.setUpdatedAt(LocalDateTime.now());

		TokenQueue savedToken = tokenQueueRepository.save(currentToken);

		return DisplayTokenResponse.builder()
				.tokenNumber(savedToken.getTokenNumber())
				.counterCode(savedToken.getCounter() != null ? savedToken.getCounter().getCounterCode() : null)
				.displayName(savedToken.getCounter() != null ? savedToken.getCounter().getDisplayName() : null)
				.status(savedToken.getStatus() != null ? savedToken.getStatus().name() : null)
				.build();
	}
}