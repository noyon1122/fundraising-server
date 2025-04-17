package com.noyon.main.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor

public class Campaign {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )

	private Long id;
	private String title;
	@Column
	private String description;
	
	private Double goalAmount;
	private Double raisedAmount=0.0;
	
	private String imageUrl;
	private String status="PENDING";
	
    private LocalDateTime createdAt=LocalDateTime.now();
    
    @ManyToOne
    private User user;
}
