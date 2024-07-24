package com.zakharovmm.noveotestassignment.mock;

import com.zakharovmm.noveotestassignment.exception.CommonAppException;
import org.springframework.stereotype.Component;

@Component
public class PaypalProcessor {

    public void makePayment(int price) throws CommonAppException {
        if (price > 100000) {
            throw new CommonAppException("Payment amount too high for Paypal");
        }
    }
}
