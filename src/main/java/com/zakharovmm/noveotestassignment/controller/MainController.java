package com.zakharovmm.noveotestassignment.controller;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.service.PurchaseService;
import com.zakharovmm.noveotestassignment.service.PriceService;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class MainController {

    private final PriceService priceService;
    private final PurchaseService purchaseService;

    @PostMapping("/calculate-price")
    public ResponseEntity<BigDecimal> calculatePrice(@RequestBody @Valid  PriceCalculationRequestDto request) {

        return ResponseEntity.ok(priceService.calculatePrice(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<Void> purchase(@RequestBody @Valid PurchaseRequestDto request) {
        purchaseService.purchase(request);

        return ResponseEntity.ok().build();

    }
}
