package com.zakharovmm.noveotestassignment.dto;

import lombok.Data;

@Data
public class PriceCalculationRequestDto {
    private Integer product;
    private String taxNumber;
    private String couponCode;
}
