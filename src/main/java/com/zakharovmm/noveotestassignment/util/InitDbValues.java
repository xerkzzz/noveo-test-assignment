package com.zakharovmm.noveotestassignment.util;

import com.zakharovmm.noveotestassignment.config.DbValuesInitConfig;
import com.zakharovmm.noveotestassignment.repository.CouponRepository;
import com.zakharovmm.noveotestassignment.repository.ProductRepository;
import com.zakharovmm.noveotestassignment.repository.TaxRepository;
import java.util.Optional;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitDbValues {

    private final DbValuesInitConfig dbValuesInitConfig;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final TaxRepository taxRepository;

    @Transactional
    @EventListener(ApplicationStartedEvent.class)
    public void initDbValues() {
        initializeIfEmpty(productRepository, dbValuesInitConfig::getProductsToInit, "products");
        initializeIfEmpty(couponRepository, dbValuesInitConfig::getCouponsToInit, "coupons");
        initializeIfEmpty(taxRepository, dbValuesInitConfig::getTaxesToInit, "taxes");
    }

    private <T> void initializeIfEmpty(JpaRepository<T, Long> repository, Supplier<Iterable<T>> dataSupplier, String entityName) {
        Optional.of(repository.count())
                .filter(count -> count == 0)
                .ifPresentOrElse(
                        count -> {
                            log.info("Initializing {}...", entityName);
                            repository.saveAll(dataSupplier.get());
                        },
                        () -> log.info("{} already initialized.", entityName)
                );
    }

}
