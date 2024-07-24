package com.zakharovmm.noveotestassignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PriceCalculationRequest {

    private Long product;
    private String taxNumber;
    private String couponCode;
}
