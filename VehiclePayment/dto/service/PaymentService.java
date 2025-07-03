public class PaymentService {
    package com.yourcompany.vehiclepayment.service;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.dto.PaymentResponseDTO;
import com.yourcompany.vehiclepayment.model.Payment;
import com.yourcompany.vehiclepayment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
    
    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequest) {
        // Validate payment request
        validatePaymentRequest(paymentRequest);
        
        // Create payment record
        Payment payment = new Payment();
        payment.setTransactionId(generateTransactionId());
        payment.setUserId(paymentRequest.getUserId());
        payment.setVehicleId(paymentRequest.getVehicleId());
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setCurrency(paymentRequest.getCurrency());
        payment.setPaymentDate(LocalDateTime.now());
        
        // Process payment based on method
        try {
            switch (paymentRequest.getPaymentMethod().toUpperCase()) {
                case "CREDIT_CARD":
                    processCreditCardPayment(payment, paymentRequest.getCardDetails());
                    break;
                case "PAYPAL":
                    processPaypalPayment(payment);
                    break;
                // Add other payment methods
                default:
                    throw new IllegalArgumentException("Unsupported payment method");
            }
            
            payment.setStatus("COMPLETED");
            paymentRepository.save(payment);
            
            return new PaymentResponseDTO(
                payment.getTransactionId(),
                payment.getStatus(),
                "Payment processed successfully",
                payment.getPaymentDate()
            );
        } catch (Exception e) {
            payment.setStatus("FAILED");
            payment.setPaymentDetails("Error: " + e.getMessage());
            paymentRepository.save(payment);
            
            throw new PaymentProcessingException("Payment processing failed: " + e.getMessage());
        }
    }
    
    private void validatePaymentRequest(PaymentRequestDTO paymentRequest) {
        if (paymentRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        // Add other validations
    }
    
    private String generateTransactionId() {
        return "TXN" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }
    
    private void processCreditCardPayment(Payment payment, PaymentCardDetailsDTO cardDetails) {
        // Implement actual credit card processing logic
        // This would integrate with a payment gateway like Stripe, PayPal, etc.
        // For now, we'll simulate a successful payment
        
        // Validate card details
        if (cardDetails == null || cardDetails.getCardNumber() == null) {
            throw new IllegalArgumentException("Invalid card details");
        }
        
        // In a real application, you would:
        // 1. Create a charge with the payment gateway
        // 2. Handle the response
        // 3. Store relevant details in payment.setPaymentDetails()
        
        payment.setPaymentDetails("Card payment processed (simulated)");
    }
    
    private void processPaypalPayment(Payment payment) {
        // Implement PayPal integration
        payment.setPaymentDetails("PayPal payment processed (simulated)");
    }
    //In exception package, create PaymentProcessingException.java
    public class PaymentProcessingException extends RuntimeException {
        public PaymentProcessingException(String message){
            super(message);
        }


    }
    // Add other payment method processors
}
}
