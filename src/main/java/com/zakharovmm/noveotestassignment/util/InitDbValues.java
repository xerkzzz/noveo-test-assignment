package com.zakharovmm.noveotestassignment.util;

import com.zakharovmm.noveotestassignment.config.AppConfig;
import com.zakharovmm.noveotestassignment.repository.CouponRepository;
import com.zakharovmm.noveotestassignment.repository.ProductRepository;
import com.zakharovmm.noveotestassignment.repository.TaxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitDbValues {

    private final AppConfig appConfig;

    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final TaxRepository taxRepository;



    @Transactional
    @EventListener(ApplicationStartedEvent.class)
    public void initDbValues() {
        productRepository.saveAll(appConfig.getProductsToInit());
        couponRepository.saveAll(appConfig.getCouponsToInit());
        taxRepository.saveAll(appConfig.getTaxesToInit());
    }

}
