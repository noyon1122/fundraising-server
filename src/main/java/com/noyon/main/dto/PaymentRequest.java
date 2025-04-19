package com.noyon.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

	private double amount;
    private String customerName;
    private String customerEmail;
    private String customerPhone;

}
