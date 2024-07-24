package com.zakharovmm.noveotestassignment.entity;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

public enum CouponType {
    D {
        @Override
        public BigDecimal applyDiscount(BigDecimal priceWithTax, BigDecimal discount) {
            return priceWithTax.subtract(discount).setScale(2, HALF_UP).max(BigDecimal.ZERO);
        }
    },
    P {
        @Override
        public BigDecimal applyDiscount(BigDecimal priceWithTax, BigDecimal discount) {
            BigDecimal discountAmount = priceWithTax.multiply(discount).divide(BigDecimal.valueOf(100), HALF_UP);
            return priceWithTax.subtract(discountAmount).setScale(2, HALF_UP).max(BigDecimal.ZERO);
        }
    };

    public abstract BigDecimal applyDiscount(BigDecimal priceWithTax, BigDecimal discount);

}
