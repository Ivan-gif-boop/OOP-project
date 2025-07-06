package com.yourcompany.vehiclepayment.strategy;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.model.PaymentResult;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PayPalProcessor implements PaymentProcessor {
    
    @Override
    public PaymentResult processPayment(PaymentRequestDTO request) {
        // Simulate PayPal processing
        boolean success = simulatePayPalPayment(request.getAmount());
        
        return PaymentResult.builder()
            .success(success)
            .message(success ? "PayPal payment processed successfully" : "PayPal payment failed")
            .transactionReference(generateTransactionReference())
            .build();
    }

    @Override
    public boolean supportsPaymentMethod(PaymentMethod method) {
        return method == PaymentMethod.PAYPAL;
    }

    @Override
    public String getProcessorName() {
        return "PayPal Processor";
    }

    private boolean simulatePayPalPayment(BigDecimal amount) {
        // Simulate PayPal processing logic
        return Math.random() > 0.05; // 95% success rate
    }

    private String generateTransactionReference() {
        return "PP_" + System.currentTimeMillis();
    }
}