package com.zakharovmm.noveotestassignment.service;

import com.zakharovmm.noveotestassignment.model.PriceCalculationRequest;
import java.math.BigDecimal;

public interface PriceService {

    BigDecimal calculatePrice(PriceCalculationRequest requestDto);
}
