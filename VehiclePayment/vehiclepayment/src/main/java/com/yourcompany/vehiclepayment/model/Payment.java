package com.yourcompany.vehiclepayment.model;

import com.yourcompany.vehiclepayment.enums.PaymentStatus;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import com.yourcompany.vehiclepayment.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.Objects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private String transactionId;
    private Long userId;
    private Long vehicleId;
    private String rentalId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private Currency currency;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String transactionReference;
    private String failureReason;

    // Business logic methods
    public boolean isSuccessful() {
        return status == PaymentStatus.COMPLETED;
    }

    public boolean canBeRefunded() {
        return status == PaymentStatus.COMPLETED && 
               paymentDate.isAfter(LocalDateTime.now().minusDays(30));
    }

    public void markAsCompleted(String transactionReference) {
        this.status = PaymentStatus.COMPLETED;
        this.transactionReference = transactionReference;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsFailed(String reason) {
        this.status = PaymentStatus.FAILED;
        this.failureReason = reason;
        this.updatedAt = LocalDateTime.now();
    }
}