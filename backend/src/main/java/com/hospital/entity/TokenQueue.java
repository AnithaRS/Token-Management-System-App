package com.hospital.entity;

import java.time.LocalDateTime;

import com.hospital.commom.BaseEntity;
import com.hospital.enums.TokenStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "token_queue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenQueue extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "token_number" , nullable = false)
    private Integer tokenNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "counter_id")
	private TokenCounter counter;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status" , nullable = false )
	private TokenStatus status;
	
	
	@Column(name = "called_time")
	private LocalDateTime calleTime;
	
	
	
}
