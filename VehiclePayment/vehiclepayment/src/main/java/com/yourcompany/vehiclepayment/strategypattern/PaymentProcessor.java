package com.yourcompany.vehiclepayment.strategy;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.model.PaymentResult;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;

// Strategy Pattern - Open/Closed Principle
public interface PaymentProcessor {
    PaymentResult processPayment(PaymentRequestDTO request);
    boolean supportsPaymentMethod(PaymentMethod method);
    String getProcessorName();
}