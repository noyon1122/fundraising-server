package com.noyon.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;

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

	    @Column(length = 1000)
	    private String description;

	    private double goalAmount;

	    private double currentAmount = 0.0;

	    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
