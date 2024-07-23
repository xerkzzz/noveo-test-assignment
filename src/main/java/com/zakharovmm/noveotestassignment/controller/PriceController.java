package com.zakharovmm.noveotestassignment.controller;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/calculate-price")
    public ResponseEntity<String> calculatePrice(@RequestBody PriceCalculationRequestDto request) {
        return ResponseEntity.ok("2.3");
    }
}
