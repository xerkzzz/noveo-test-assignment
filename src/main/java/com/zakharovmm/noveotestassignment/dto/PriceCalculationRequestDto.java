package com.zakharovmm.noveotestassignment.dto;

import com.zakharovmm.noveotestassignment.validator.ValidCoupon;
import com.zakharovmm.noveotestassignment.validator.ValidTaxNumber;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceCalculationRequestDto {

    @NotNull(message = "Product id should be presented")
    private Long product;

    @ValidTaxNumber(message = "The tax number must be presented and follow the correct format.")
    private String taxNumber;

    @ValidCoupon
    private String couponCode;
}
