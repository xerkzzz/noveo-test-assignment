package com.zakharovmm.noveotestassignment.config;

import com.zakharovmm.noveotestassignment.entity.Coupon;
import com.zakharovmm.noveotestassignment.entity.Product;
import com.zakharovmm.noveotestassignment.entity.Tax;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class DbValuesInitConfig {
    private List<Product> productsToInit = new ArrayList<>();
    private List<Coupon> couponsToInit = new ArrayList<>();
    private List<Tax> taxesToInit = new ArrayList<>();
}