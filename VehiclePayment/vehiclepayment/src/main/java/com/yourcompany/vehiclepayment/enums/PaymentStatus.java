package com.yourcompany.vehiclepayment.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum PaymentStatus {
    PENDING("Payment is being processed", false),
    COMPLETED("Payment completed successfully", true),
    FAILED("Payment failed", false),
    CANCELLED("Payment was cancelled", false),
    REFUNDED("Payment was refunded", true),
    PARTIALLY_REFUNDED("Payment was partially refunded", true),
    IN_REVIEW("Payment is under review", false);

    private final String description;
    private final boolean isSuccessful;
    
    private static final Map<String, PaymentStatus> nameToValueMap = 
        Arrays.stream(values())
            .collect(Collectors.toMap(
                Enum::name,
                Function.identity()
            ));

    PaymentStatus(String description, boolean isSuccessful) {
        this.description = description;
        this.isSuccessful = isSuccessful;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isTerminalState() {
        return this != PENDING && this != IN_REVIEW;
    }

    public static PaymentStatus fromString(String name) {
        PaymentStatus status = nameToValueMap.get(name);
        if (status == null) {
            throw new IllegalArgumentException(
                String.format("No enum constant %s for name %s", 
                PaymentStatus.class.getName(), name)
            );
        }
        return status;
    }

    public static boolean isValidStatus(String name) {
        return nameToValueMap.containsKey(name);
    }
}