package com.zakharovmm.noveotestassignment.model;

import lombok.Data;

@Data
public class PurchaseRequest {

    private Long product;
    private String taxNumber;
    private String couponCode;
    PaymentProcessorType paymentProcessor;
}
