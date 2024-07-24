package com.zakharovmm.noveotestassignment.service;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import java.math.BigDecimal;

public interface PriceService {

    BigDecimal calculatePrice(PriceCalculationRequestDto requestDto);
}
