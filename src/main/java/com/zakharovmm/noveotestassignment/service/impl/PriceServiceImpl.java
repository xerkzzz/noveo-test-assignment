package com.zakharovmm.noveotestassignment.service.impl;


import com.zakharovmm.noveotestassignment.exception.CommonAppException;
import com.zakharovmm.noveotestassignment.model.PriceCalculationRequest;
import com.zakharovmm.noveotestassignment.repository.CouponRepository;
import com.zakharovmm.noveotestassignment.repository.ProductRepository;
import com.zakharovmm.noveotestassignment.repository.TaxRepository;
import com.zakharovmm.noveotestassignment.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final TaxRepository taxRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;

    @Override
    public BigDecimal calculatePrice(PriceCalculationRequest requestDto) {

        var tax = taxRepository.findTaxByTaxNumber(requestDto.getTaxNumber())
                .orElseThrow(() -> new CommonAppException(String.format("Can't calculate tax for tax number [%s].", requestDto.getTaxNumber())));
        var product = productRepository.findById(requestDto.getProduct())
                .orElseThrow(() -> new CommonAppException(String.format("Product with id [%d] not found.", requestDto.getProduct())));
        var couponOptional = Optional.ofNullable(requestDto.getCouponCode())
                .map(String::trim)
                .map(String::toUpperCase)
                .flatMap(couponRepository::findByCode);

        var basePrice = product.getPrice();

        var taxPercent = BigDecimal.valueOf(tax.getTaxPercent());
        var taxAmount = basePrice.multiply(taxPercent).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        var priceWithTax = basePrice.add(taxAmount);

        return couponOptional.map(coupon ->
                        coupon.getCouponType().applyDiscount(priceWithTax, BigDecimal.valueOf(coupon.getDiscount())))
                .orElse(priceWithTax);
    }
}
