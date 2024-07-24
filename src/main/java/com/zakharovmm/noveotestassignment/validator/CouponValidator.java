package com.zakharovmm.noveotestassignment.validator;


import com.zakharovmm.noveotestassignment.repository.CouponRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CouponValidator implements ConstraintValidator<ValidCoupon, String> {

    private final CouponRepository couponRepository;
    private Set<Pattern> patterns;

    @PostConstruct
    void init() {
        updatePatterns();
    }

    @Override
    public boolean isValid(String coupon, ConstraintValidatorContext context) {
        return Optional.ofNullable(coupon)
                .map(c -> {
                    if (checkValidity(c)) {
                        return true;
                    } else {
                        updatePatterns();
                        return checkValidity(c);
                    }
                })
                .orElse(true);
    }

    private void updatePatterns() {
        patterns = couponRepository.findAllCouponCodes().stream()
                .map(Pattern::compile)
                .collect(Collectors.toSet());
    }

    private boolean checkValidity(String coupon) {
        return patterns.stream().anyMatch(pattern -> pattern.matcher(coupon).matches());
    }
}
