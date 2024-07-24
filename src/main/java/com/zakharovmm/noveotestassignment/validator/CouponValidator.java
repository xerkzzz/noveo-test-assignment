package com.zakharovmm.noveotestassignment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CouponValidator implements ConstraintValidator<ValidTaxNumber, String> {

    private static final String COUPON_PATTERN = "^(P\\d{1,2}|P100|D\\d+)$";

    @Override
    public boolean isValid(String coupon, ConstraintValidatorContext context) {
        if (coupon == null) {
            return false;
        }
        return coupon.matches(COUPON_PATTERN);
    }
}
