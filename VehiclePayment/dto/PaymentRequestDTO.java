package com.yourcompany.vehiclepayment.dto;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long userId;
    private Long vehicleId;
    private String paymentMethod;
    private BigDecimal amount;
    private String currency;
    private PaymentCardDetailsDTO cardDetails;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentCardDetailsDTO getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(PaymentCardDetailsDTO cardDetails) {
        this.cardDetails = cardDetails;
    }
}