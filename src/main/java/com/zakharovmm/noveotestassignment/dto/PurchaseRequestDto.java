package com.zakharovmm.noveotestassignment.dto;

import com.zakharovmm.noveotestassignment.validator.ValidCoupon;
import com.zakharovmm.noveotestassignment.validator.ValidTaxNumber;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseRequestDto {
    @NotNull
    private Integer product;
    @ValidTaxNumber
    private String taxNumber;
    @ValidCoupon
    private String couponCode;
    PaymentProcessorType paymentProcessor;
}
