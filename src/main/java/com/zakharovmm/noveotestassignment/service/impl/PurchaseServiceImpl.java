package com.zakharovmm.noveotestassignment.service.impl;

import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.mock.PaypalProcessor;
import com.zakharovmm.noveotestassignment.mock.StripeProcessor;
import com.zakharovmm.noveotestassignment.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PaypalProcessor paypalProcessor;
    private final StripeProcessor stripeProcessor;


    @Override
    public void purchase(PurchaseRequestDto request) {

        switch (request.getPaymentProcessor()){
            case PAYPAL -> {
                try {
                    paypalProcessor.makePayment(1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case STRIPE -> stripeProcessor.pay(1.0f);
        }

    }
}
