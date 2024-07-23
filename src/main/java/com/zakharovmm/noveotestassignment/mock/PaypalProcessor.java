package com.zakharovmm.noveotestassignment.mock;

import org.springframework.stereotype.Component;

@Component
public class PaypalProcessor {
    public void makePayment(int price) throws Exception {
        if (price > 100000) {
            throw new Exception("Payment amount too high for Paypal");
        }
    }
}
