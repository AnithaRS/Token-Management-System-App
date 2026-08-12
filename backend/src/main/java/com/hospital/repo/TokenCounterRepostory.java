package com.hospital.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.TokenCounter;

public interface TokenCounterRepostory extends JpaRepository<TokenCounter, Long>{

	Optional<TokenCounter> findByCounterCode(String counterCode);
}
