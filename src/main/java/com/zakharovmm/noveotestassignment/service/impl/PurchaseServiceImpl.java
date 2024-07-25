package com.zakharovmm.noveotestassignment.service.impl;

import com.zakharovmm.noveotestassignment.dto.PaypalPurchaseResponse;
import com.zakharovmm.noveotestassignment.dto.PurchaseResponse;
import com.zakharovmm.noveotestassignment.dto.StripePurchaseResponse;
import com.zakharovmm.noveotestassignment.exception.CommonAppException;
import com.zakharovmm.noveotestassignment.mapper.PriceCalculationMapper;
import com.zakharovmm.noveotestassignment.mock.PaypalProcessor;
import com.zakharovmm.noveotestassignment.mock.StripeProcessor;
import com.zakharovmm.noveotestassignment.model.PurchaseRequest;
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
    private final PriceCalculationMapper priceCalculationMapper;


    @Override
    public PurchaseResponse purchase(PurchaseRequest request) {

        var price = priceService.calculatePrice(priceCalculationMapper.map(request));

        return switch (request.getPaymentProcessor()) {
            case PAYPAL -> {
                try {
                    paypalProcessor.makePayment(price.intValue());
                    yield new PaypalPurchaseResponse();
                } catch (Exception e) {
                    throw new CommonAppException(String.format("Paypal payment exception for [%s]", request));
                }
            }
            case STRIPE -> new StripePurchaseResponse(stripeProcessor.pay(price.floatValue()));
            default ->
                    throw new CommonAppException(String.format("Unknown payment processor [%s]", request.getPaymentProcessor()));
        };
    }
}
