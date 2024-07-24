package com.zakharovmm.noveotestassignment.dto;

import com.zakharovmm.noveotestassignment.validator.ValidCoupon;
import com.zakharovmm.noveotestassignment.validator.ValidTaxNumber;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PriceCalculationRequestDto {

    @NotNull(message = "Product id should be presented")
    private Long product;

    @ValidTaxNumber
    private String taxNumber;

    @ValidCoupon
    private String couponCode;
}
