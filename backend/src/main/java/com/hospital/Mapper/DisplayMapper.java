package com.hospital.Mapper;

import org.springframework.stereotype.Component;

import com.hospital.DtoResponse.DisplayTokenResponse;
import com.hospital.entity.TokenQueue;

@Component

public class DisplayMapper {

	public DisplayTokenResponse  toDisplayTokenResponse(TokenQueue  tokenQueue  ) {
		return DisplayTokenResponse.builder()
				.tokenNumber(tokenQueue.getTokenNumber())
				.counterCode(tokenQueue.getCounter()!=null ? tokenQueue.getCounter().getCounterCode():null)
				.counterName(tokenQueue.getCounter()!=null ? tokenQueue.getCounter().getCounterName():null)
				.displayName(tokenQueue.getCounter()!=null ? tokenQueue.getCounter().getDisplayName():null)
				.status(tokenQueue.getStatus()!=null ? tokenQueue.getStatus().name():null).build();
	}
}
