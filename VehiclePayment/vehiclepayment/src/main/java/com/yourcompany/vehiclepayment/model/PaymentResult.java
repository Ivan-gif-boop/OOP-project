package com.yourcompany.vehiclepayment.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResult {
    private boolean success;
    private String message;
    private String transactionReference;
    private String errorCode;
    private Object additionalData;
}