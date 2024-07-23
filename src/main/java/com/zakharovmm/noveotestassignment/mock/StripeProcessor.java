package com.zakharovmm.noveotestassignment.mock;

import org.springframework.stereotype.Component;

@Component
public class StripeProcessor {
    public boolean pay(float price) {
        return price >= 100;
    }
}