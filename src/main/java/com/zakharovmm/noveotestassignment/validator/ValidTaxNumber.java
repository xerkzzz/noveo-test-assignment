package com.zakharovmm.noveotestassignment.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TaxNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaxNumber {
    String message() default "Invalid tax number or not available trade country";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
