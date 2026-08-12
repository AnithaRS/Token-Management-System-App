package com.hospital.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.hospital.entity.TokenQueue;
import com.hospital.enums.TokenStatus;

import jakarta.persistence.LockModeType;

public interface TokenQueueRepository extends JpaRepository<TokenQueue, Long>{

	List<TokenQueue> findByStatus(TokenStatus status);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)

	Optional<TokenQueue> findFirstByStatusOrderByTokenNumberAsc(TokenStatus status);	
	@Query("SELECT MAX(t.tokenNumber) FROM TokenQueue t") 
	Integer findMaxTokenNumber();
	
	Optional<TokenQueue> findFirstByCounter_counterCodeAndStatus(String counterCode,TokenStatus status);

}
