package com.yourcompany.vehiclepayment.exception;

import com.yourcompany.vehiclepayment.enums.PaymentErrorCode;

/**
 * Custom exception for payment processing failures.
 * Includes error codes and supports internationalization.
 */
public class PaymentProcessingException extends RuntimeException {
    private final PaymentErrorCode errorCode;
    private final transient Object[] args;

    public PaymentProcessingException(PaymentErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.errorCode = errorCode;
        this.args = args;
    }

    public PaymentProcessingException(PaymentErrorCode errorCode, Throwable cause, Object... args) {
        super(errorCode.formatMessage(args), cause);
        this.errorCode = errorCode;
        this.args = args;
    }

    public PaymentErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args != null ? args.clone() : new Object[0];
    }

    /**
     * Returns a user-friendly message with error details
     */
    public String getUserMessage() {
        return "Payment Error [" + errorCode + "]: " + getMessage();
    }
}