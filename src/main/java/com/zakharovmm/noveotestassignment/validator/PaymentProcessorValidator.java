package com.zakharovmm.noveotestassignment.validator;


import com.zakharovmm.noveotestassignment.model.PaymentProcessorType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PaymentProcessorValidator implements ConstraintValidator<ValidPaymentProcessor, String> {

    @Override
    public boolean isValid(String paymentProcessor, ConstraintValidatorContext context) {
        if (paymentProcessor == null || paymentProcessor.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Payment processor type should be presented")
                    .addConstraintViolation();
            return false;
        }

        try {
            PaymentProcessorType.fromValue(paymentProcessor);
            return true;
        } catch (IllegalArgumentException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            String.format("Unknown payment processor type: %s. Valid values are: %s", paymentProcessor, getValidValues()))
                    .addConstraintViolation();
            return false;
        }
    }

    private List<String> getValidValues() {
        return Arrays.stream(PaymentProcessorType.values())
                .map(PaymentProcessorType::getValue)
                .toList();
    }
}

