package com.yourcompany.vehiclepayment.service;

import com.yourcompany.vehiclepayment.model.Payment;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentNotificationService {
    
    public void notifyPaymentSuccess(Payment payment) {
        log.info("Payment successful - Transaction ID: {}, User: {}", 
                payment.getTransactionId(), payment.getUserId());
        
        // In a real application, send email/SMS notifications
        // emailService.sendPaymentSuccessNotification(payment);
    }

    public void notifyPaymentFailure(Payment payment, String reason) {
        log.warn("Payment failed - Transaction ID: {}, User: {}, Reason: {}", 
                payment.getTransactionId(), payment.getUserId(), reason);
        
        // In a real application, send failure notifications
        // emailService.sendPaymentFailureNotification(payment, reason);
    }
}