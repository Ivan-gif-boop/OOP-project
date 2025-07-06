package com.yourcompany.vehiclepayment.strategy;

import com.yourcompany.vehiclepayment.dto.PaymentRequestDTO;
import com.yourcompany.vehiclepayment.dto.PaymentCardDetailsDTO;
import com.yourcompany.vehiclepayment.model.PaymentResult;
import com.yourcompany.vehiclepayment.enums.PaymentMethod;
import com.yourcompany.vehiclepayment.validator.CardValidator;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreditCardProcessor implements PaymentProcessor {
    private final CardValidator cardValidator;

    @Override
    public PaymentResult processPayment(PaymentRequestDTO request) {
        PaymentCardDetailsDTO cardDetails = request.getCardDetails();
        
        // Validate card details
        if (!cardValidator.isValid(cardDetails)) {
            return PaymentResult.builder()
                .success(false)
                .message("Invalid card details")
                .errorCode("INVALID_CARD")
                .build();
        }

        // Simulate payment processing
        boolean success = simulateCardPayment(cardDetails);
        
        return PaymentResult.builder()
            .success(success)
            .message(success ? "Card payment processed successfully" : "Card payment failed")
            .transactionReference(generateTransactionReference())
            .build();
    }

    @Override
    public boolean supportsPaymentMethod(PaymentMethod method) {
        return method == PaymentMethod.CREDIT_CARD || method == PaymentMethod.DEBIT_CARD;
    }

    @Override
    public String getProcessorName() {
        return "Credit Card Processor";
    }

    private boolean simulateCardPayment(PaymentCardDetailsDTO cardDetails) {
        // Simulate different scenarios based on card number
        String cardNumber = cardDetails.getCardNumber();
        if (cardNumber.endsWith("0000")) {
            return false; // Simulate declined card
        }
        return Math.random() > 0.1; // 90% success rate
    }

    private String generateTransactionReference() {
        return "CC_" + System.currentTimeMillis();
    }
}