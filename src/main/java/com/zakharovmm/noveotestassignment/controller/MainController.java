package com.zakharovmm.noveotestassignment.controller;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.handler.PurchaseResponseHandler;
import com.zakharovmm.noveotestassignment.mapper.PriceCalculationMapper;
import com.zakharovmm.noveotestassignment.mapper.PurchaseMapper;
import com.zakharovmm.noveotestassignment.service.PriceService;
import com.zakharovmm.noveotestassignment.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class MainController {

    private final PriceService priceService;
    private final PurchaseService purchaseService;

    private final PriceCalculationMapper priceCalculationMapper;
    private final PurchaseMapper purchaseMapper;

    private final PurchaseResponseHandler responseHandler;

    @PostMapping("/calculate-price")
    public ResponseEntity<BigDecimal> calculatePrice(@RequestBody @Valid PriceCalculationRequestDto request) {

        return ResponseEntity.ok(priceService.calculatePrice(priceCalculationMapper.map(request)));
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody @Valid PurchaseRequestDto request) {
        var response = purchaseService.purchase(purchaseMapper.map(request));

        return responseHandler.handleResponse(response);
    }
}
