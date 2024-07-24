package com.zakharovmm.noveotestassignment.dto;

import com.zakharovmm.noveotestassignment.validator.ValidCoupon;
import com.zakharovmm.noveotestassignment.validator.ValidTaxNumber;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseRequestDto {

    @NotNull(message = "Product id should be presented")
    private Long product;

    @ValidTaxNumber
    private String taxNumber;

    @ValidCoupon
    private String couponCode;

    @NotNull(message = "Payment processor type should be presented")
    PaymentProcessorType paymentProcessor;
}
