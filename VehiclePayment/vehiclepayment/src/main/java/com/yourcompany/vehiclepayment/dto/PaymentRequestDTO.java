package com.yourcompany.vehiclepayment.dto;

import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import com.yourcompany.vehiclepayment.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    private Long userId;

    @NotNull(message = "Vehicle ID is required")
    @Positive(message = "Vehicle ID must be positive")
    private Long vehicleId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Amount format is invalid")
    private BigDecimal amount;

    @NotNull(message = "Currency is required")
    private Currency currency;

    private PaymentCardDetailsDTO cardDetails;

    private String rentalId; // Link to rental transaction

    // Validation method
    public void validatePaymentDetails() {
        if (paymentMethod == PaymentMethod.CREDIT_CARD || paymentMethod == PaymentMethod.DEBIT_CARD) {
            if (cardDetails == null) {
                throw new IllegalArgumentException("Card details are required for card payments");
            }
        }
    }
}