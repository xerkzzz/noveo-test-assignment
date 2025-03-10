package com.zakharovmm.noveotestassignment.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CouponValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCoupon {
    String message() default "Invalid or not existing coupon code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
