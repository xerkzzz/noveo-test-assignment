package com.zakharovmm.noveotestassignment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaxNumberValidator implements ConstraintValidator<ValidTaxNumber, String> {

    private static final String TAX_NUMBER_PATTERN = "^(DE\\d{9}|IT\\d{11}|GR\\d{9}|FR[A-Z]{2}\\d{9})$";
    @Override
    public boolean isValid(String taxNumber, ConstraintValidatorContext context) {
        if (taxNumber == null) {
            return false;
        }
        return taxNumber.matches(TAX_NUMBER_PATTERN);
    }
}
