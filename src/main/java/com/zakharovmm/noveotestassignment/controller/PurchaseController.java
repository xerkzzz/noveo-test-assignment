package com.zakharovmm.noveotestassignment.controller;


import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    private final PaymentService paymentService;

    @PostMapping("/purchase")
    public ResponseEntity<Void> purchase(@RequestBody PurchaseRequestDto purchaseRequest) {

        return ResponseEntity.ok().build();

    }
}