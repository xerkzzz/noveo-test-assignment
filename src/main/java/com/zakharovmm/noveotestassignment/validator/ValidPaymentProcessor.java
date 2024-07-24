package com.zakharovmm.noveotestassignment.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PaymentProcessorValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPaymentProcessor {
    String message() default "Invalid payment processor type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
