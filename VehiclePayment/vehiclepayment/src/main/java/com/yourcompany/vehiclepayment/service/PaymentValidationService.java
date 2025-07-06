package com.yourcompany.vehiclepayment.service;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.exception.PaymentValidationException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentValidationService {
    
    public void validatePaymentRequest(PaymentRequestDTO request) {
        if (request == null) {
            throw new PaymentValidationException("Payment request cannot be null");
        }
        
        request.validatePaymentDetails();
        
        // Additional business validations
        validateAmount(request.getAmount());
        validateUserAndVehicle(request.getUserId(), request.getVehicleId());
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PaymentValidationService("Amount must be positive");
        }
        
        if (amount.compareTo(new BigDecimal("10000")) > 0) {
            throw new PaymentValidationService("Amount exceeds maximum limit");
        }
    }

    private void validateUserAndVehicle(Long userId, Long vehicleId) {
        // In a real application, you would check if user and vehicle exist
        if (userId == null || userId <= 0) {
            throw new PaymentValidationService("Invalid user ID");
        }
        
        if (vehicleId == null || vehicleId <= 0) {
            throw new PaymentValidationService("Invalid vehicle ID");
        }
    }

    public PaymentValidationService(String string) {
        //TODO Auto-generated constructor stub
    }
}