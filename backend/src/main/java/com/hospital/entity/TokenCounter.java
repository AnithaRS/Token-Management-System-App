package com.hospital.entity;
import com.hospital.commom.BaseEntity;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "token_counter")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenCounter   extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "counter_code" , nullable = false , unique = true)
    private String counterCode;
	
	@Column(name = "display_name" , nullable = false)
	private String displayName;
	
	
	@Column(name = "active" , nullable = false)
	private boolean active;
	
	@Column(name = "counter_name", nullable = false)
	private String counterName;

	public String getCounterName() {
	    return counterName;
	}
	public void setCounterName(String counterName) {
	    this.counterName = counterName;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCounterCode() {
		return counterCode;
	}
	public void setCounterCode(String counterCode) {
		this.counterCode = counterCode;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
	
}