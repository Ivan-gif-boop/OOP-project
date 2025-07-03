package com.yourcompany.vehiclepayment.dto;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCardDetailsDTO {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    // Getters and setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolder