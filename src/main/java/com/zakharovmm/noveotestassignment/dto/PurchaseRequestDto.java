package com.zakharovmm.noveotestassignment.dto;

import lombok.Data;

@Data
public class PurchaseRequestDto {
    private Integer product;
    private String taxNumber;
    private String couponCode;
    PaymentProcessorType paymentProcessor;
}
