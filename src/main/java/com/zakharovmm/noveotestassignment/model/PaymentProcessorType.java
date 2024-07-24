package com.zakharovmm.noveotestassignment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PaymentProcessorType {
    PAYPAL("paypal"),
    STRIPE("stripe");

    private final String value;

    PaymentProcessorType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PaymentProcessorType fromValue(String value) {
        for (PaymentProcessorType type : PaymentProcessorType.values()) {
            if (type.getValue().equalsIgnoreCase(value.trim())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown payment processor type: " + value);
    }
}
