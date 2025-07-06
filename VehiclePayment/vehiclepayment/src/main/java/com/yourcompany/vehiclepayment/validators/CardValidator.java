package com.yourcompany.vehiclepayment.validator;

import com.yourcompany.vehiclepayment.dto.PaymentCardDetailsDTO;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CardValidator {
    
    public boolean isValid(PaymentCardDetailsDTO cardDetails) {
        return isValidCardNumber(cardDetails.getCardNumber()) &&
               isValidExpiryDate(cardDetails.getExpiryDate()) &&
               isValidCvv(cardDetails.getCvv()) &&
               isValidCardHolderName(cardDetails.getCardHolderName());
    }
    
    private boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return false;
        }
        
        // Remove spaces and dashes
        String cleanNumber = cardNumber.replaceAll("[\\s-]", "");
        
        // Check length and digits only
        if (cleanNumber.length() < 13 || cleanNumber.length() > 19) {
            return false;
        }
        
        return cleanNumber.matches("\\d+") && luhnCheck(cleanNumber);
    }
    
    private boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        
        return (sum % 10 == 0);
    }
    
    private boolean isValidExpiryDate(String expiryDate) {
        if (expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }
        
        try {
            // Parse MM/YY format
            String[] parts = expiryDate.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);
            
            // Validate month
            if (month < 1 || month > 12) {
                return false;
            }
            
            // Convert 2-digit year to 4-digit (assuming 20xx)
            if (year < 100) {
                year += 2000;
            }
            
            // Create expiry date (last day of the month)
            LocalDate expiryDateObj = LocalDate.of(year, month, 1)
                    .plusMonths(1)
                    .minusDays(1);
            
            // Check if expiry date is in the future
            return !expiryDateObj.isBefore(LocalDate.now());
            
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean isValidCvv(String cvv) {
        return cvv != null && cvv.matches("\\d{3,4}");
    }
    
    private boolean isValidCardHolderName(String name) {
        return name != null && name.trim().length() >= 2 &&
               name.matches("^[a-zA-Z\\s]+$");
    }
}