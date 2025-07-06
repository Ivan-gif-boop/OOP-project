package com.yourcompany.vehiclepayment.controller;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.dto.PaymentResponseDTO;
import com.yourcompany.vehiclepayment.service.PaymentService;
import com.yourcompany.vehiclepayment.exception.PaymentValidationException;
import com.yourcompany.vehiclepayment.exception.PaymentProcessingException;
import com.yourcompany.vehiclepayment.enums.PaymentStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(origins = "*")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(
            @Valid @RequestBody PaymentRequestDTO request) {
        
        try {
            log.info("Processing payment for vehicle: {}, amount: {}", 
                     request.getVehicleId(), request.getAmount());
            
            PaymentResponseDTO response = paymentService.processPayment(request);
            
            return ResponseEntity
                .status(mapStatusToHttpStatus(response.getStatus()))
                .body(response);
            
        } catch (PaymentValidationException e) {
            log.warn("Validation failed for payment: {}", e.getMessage());
            return ResponseEntity
                .badRequest()
                .body(buildErrorResponse(e.getMessage(), PaymentStatus.FAILED));
                
        } catch (PaymentProcessingException e) {
            log.error("Payment processing failed: {}", e.getMessage());
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse(e.getMessage(), PaymentStatus.FAILED));
                
        } catch (Exception e) {
            log.error("Unexpected error during payment processing: {}", e.getMessage(), e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse("Internal server error", PaymentStatus.FAILED));
        }
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentStatus(
            @PathVariable @NotBlank String transactionId) {
        
        try {
            log.info("Fetching status for transaction: {}", transactionId);
            PaymentResponseDTO response = paymentService.getPaymentStatus(transactionId);
            return ResponseEntity.ok(response);
            
        } catch (PaymentProcessingException e) {
            log.error("Error retrieving payment status: {}", e.getMessage());
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(buildErrorResponse(e.getMessage(), PaymentStatus.UNKNOWN));
                
        } catch (Exception e) {
            log.error("Unexpected error fetching payment status: {}", e.getMessage(), e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse("Internal server error", PaymentStatus.UNKNOWN));
        }
    }

    private HttpStatus mapStatusToHttpStatus(PaymentStatus status) {
        return switch (status) {
            case COMPLETED -> HttpStatus.OK;
            case PENDING -> HttpStatus.ACCEPTED;
            case FAILED, CANCELLED -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    private PaymentResponseDTO buildErrorResponse(String message, PaymentStatus status) {
        return PaymentResponseDTO.builder()
            .status(status)
            .message(message)
            .build();
    }
}