package com.zakharovmm.noveotestassignment.dto;

public enum PaymentProcessorType {
    PAYPAL("paypal"),
    STRIPE("stripe");

    private final String value;

    PaymentProcessorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentProcessorType fromValue(String value) {
        for (PaymentProcessorType type : PaymentProcessorType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown payment processor type: " + value);
    }
}
