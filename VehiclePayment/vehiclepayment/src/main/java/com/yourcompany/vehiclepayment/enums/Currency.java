package com.yourcompany.vehiclepayment.enums;

public enum Currency {
    USD("US Dollar"),
    EUR("Euro"),
    GBP("British Pound"),
    KES("Kenyan Shilling");

    private final String displayName;

    Currency(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}