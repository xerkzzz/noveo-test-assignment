package com.zakharovmm.noveotestassignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StripePurchaseResponse implements PurchaseResponse{
    private boolean result;
}
