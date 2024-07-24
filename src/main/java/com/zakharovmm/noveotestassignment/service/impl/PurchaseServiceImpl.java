package com.zakharovmm.noveotestassignment.service.impl;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.exception.CommonAppException;
import com.zakharovmm.noveotestassignment.mock.PaypalProcessor;
import com.zakharovmm.noveotestassignment.mock.StripeProcessor;
import com.zakharovmm.noveotestassignment.service.PriceService;
import com.zakharovmm.noveotestassignment.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PriceService priceService;
    private final PaypalProcessor paypalProcessor;
    private final StripeProcessor stripeProcessor;


    @Override
    public String purchase(PurchaseRequestDto request) {
        var price = priceService.calculatePrice(PriceCalculationRequestDto.builder()
                .product(request.getProduct())
                .taxNumber(request.getTaxNumber())
                .couponCode(request.getCouponCode())
                .build());

      return switch (request.getPaymentProcessor()) {
            case PAYPAL -> {
                try {
                    paypalProcessor.makePayment(price.intValue());
                    yield "";
                } catch (Exception e) {
                    throw new CommonAppException(String.format("Paypal payment exception for [%s]", request));
                }
            }
            case STRIPE -> String.valueOf(stripeProcessor.pay(price.floatValue()));
            default -> throw new CommonAppException(String.format("Unknown payment processor [%s]", request.getPaymentProcessor()));
        };

    }
}
