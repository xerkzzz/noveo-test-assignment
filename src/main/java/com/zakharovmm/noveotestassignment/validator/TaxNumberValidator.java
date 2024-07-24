package com.zakharovmm.noveotestassignment.validator;


import com.zakharovmm.noveotestassignment.repository.TaxRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.zakharovmm.noveotestassignment.entity.CountryCode.existsByCode;
import static com.zakharovmm.noveotestassignment.entity.CountryCode.getAllCountriesAndCodes;


@RequiredArgsConstructor
@Component
public class TaxNumberValidator implements ConstraintValidator<ValidTaxNumber, String> {

    private final TaxRepository taxRepository;
    private Set<Pattern> patterns;

    @PostConstruct
    void init() {
        updatePatterns();
    }

    @Override
    public boolean isValid(String taxNumber, ConstraintValidatorContext context) {
        if (taxNumber == null || taxNumber.isEmpty()) {
            return setValidationErrorMessage(context, "Tax number cannot be null or empty.");
        }

        if (!checkCountryValidity(taxNumber)) {
            return setValidationErrorMessage(context, String.format("Not possible to trade with your country. Available countries: %s.",
                    getAllCountriesAndCodes()));
        }

        if (!checkFormatValidity(taxNumber)) {
            return setValidationErrorMessage(context, String.format("Invalid tax number format [%s].", taxNumber));
        }

        return true;
    }

    private static boolean setValidationErrorMessage(ConstraintValidatorContext context, String taxNumber) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        taxNumber)
                .addConstraintViolation();
        return false;
    }

    private void updatePatterns() {
        patterns = taxRepository.findAllPatterns().stream()
                .map(Pattern::compile)
                .collect(Collectors.toSet());
    }

    private boolean checkFormatValidity(String taxNumber) {
        return patterns.stream().anyMatch(pattern -> pattern.matcher(taxNumber).matches());
    }


    private boolean checkCountryValidity(String taxNumber) {
        if (taxNumber.length() < 2) {
            return false;
        }

        return existsByCode(taxNumber.substring(0, 2));

    }
}



