package com.yourcompany.vehiclepayment.service;

import com.yourcompany.vehiclepayment.dto.*;
import com.yourcompany.vehiclepayment.model.Payment;
import com.yourcompany.vehiclepayment.model.PaymentResult;
import com.yourcompany.vehiclepayment.enums.PaymentStatus;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import com.yourcompany.vehiclepayment.strategy.PaymentProcessor;
import com.yourcompany.vehiclepayment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final List<PaymentProcessor> paymentProcessors;
    private final PaymentRepository paymentRepository;
    private final PaymentValidationService validationService;
    private final PaymentNotificationService notificationService;

    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        log.info("Processing payment for user: {}, vehicle: {}", 
                request.getUserId(), request.getVehicleId());
        
        // Validate request
        validationService.validatePaymentRequest(request);
        
        // Create payment record
        Payment payment = createPaymentRecord(request);
        
        try {
            // Find appropriate processor
            PaymentProcessor processor = findProcessor(request.getPaymentMethod());
            
            // Process payment
            PaymentResult result = processor.processPayment(request);
            
            // Update payment status
            if (result.isSuccess()) {
                payment.markAsCompleted(result.getTransactionReference());
                notificationService.notifyPaymentSuccess(payment);
            } else {
                payment.markAsFailed(result.getMessage());
                notificationService.notifyPaymentFailure(payment, result.getMessage());
            }
            
            // Save payment
            paymentRepository.save(payment);
            
            return buildResponse(payment, result);
            
        } catch (Exception e) {
            log.error("Payment processing failed: {}", e.getMessage(), e);
            payment.markAsFailed(e.getMessage());
            paymentRepository.save(payment);
            
            return buildErrorResponse(payment, e.getMessage());
        }
    }

    private PaymentProcessor findProcessor(PaymentMethod method) {
        return paymentProcessors.stream()
            .filter(processor -> processor.supportsPaymentMethod(method))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No processor found for payment method: " + method));
    }

    private Payment createPaymentRecord(PaymentRequestDTO request) {
        return Payment.builder()
            .transactionId(UUID.randomUUID().toString())
            .userId(request.getUserId())
            .vehicleId(request.getVehicleId())
            .rentalId(request.getRentalId())
            .paymentMethod(request.getPaymentMethod())
            .amount(request.getAmount())
            .currency(request.getCurrency())
            .status(PaymentStatus.PENDING)
            .paymentDate(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .build();
    }

    private PaymentResponseDTO buildResponse(Payment payment, PaymentResult result) {
        return PaymentResponseDTO.builder()
            .transactionId(payment.getTransactionId())
            .status(payment.getStatus())
            .amount(payment.getAmount())
            .currency(payment.getCurrency())
            .paymentDate(payment.getPaymentDate())
            .paymentMethod(payment.getPaymentMethod())
            .rentalId(payment.getRentalId())
            .message(result.getMessage())
            .build();
    }

    private PaymentResponseDTO buildErrorResponse(Payment payment, String errorMessage) {
        return PaymentResponseDTO.builder()
            .transactionId(payment.getTransactionId())
            .status(PaymentStatus.FAILED)
            .amount(payment.getAmount())
            .currency(payment.getCurrency())
            .paymentDate(payment.getPaymentDate())
            .paymentMethod(payment.getPaymentMethod())
            .rentalId(payment.getRentalId())
            .message(errorMessage)
            .build();
    }
}