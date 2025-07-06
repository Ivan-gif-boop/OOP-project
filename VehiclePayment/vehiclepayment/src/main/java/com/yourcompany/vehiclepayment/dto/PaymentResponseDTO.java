package com.yourcompany.vehiclepayment.dto;

import com.yourcompany.vehiclepayment.enums.PaymentStatus;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import com.yourcompany.vehiclepayment.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {
    private String transactionId;
    private PaymentStatus status;
    private BigDecimal amount;
    private Currency currency;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private String message;
    private String rentalId;
    private String receiptUrl; // For generating receipts
}