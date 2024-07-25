package com.zakharovmm.noveotestassignment.handler;

import com.zakharovmm.noveotestassignment.dto.PaypalPurchaseResponse;
import com.zakharovmm.noveotestassignment.dto.PurchaseResponse;
import com.zakharovmm.noveotestassignment.dto.StripePurchaseResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class PurchaseResponseHandler {

    private final Map<Class<? extends PurchaseResponse>, Function<PurchaseResponse, ResponseEntity<String>>> responseHandlers = new HashMap<>();

    @PostConstruct
    private void initResponseHandlers() {
        responseHandlers.put(PaypalPurchaseResponse.class, response -> ResponseEntity.ok().build());
        responseHandlers.put(StripePurchaseResponse.class, response -> {
            boolean result = ((StripePurchaseResponse) response).isResult();
            return ResponseEntity.ok(String.valueOf(result));
        });
    }

    public ResponseEntity<String> handleResponse(PurchaseResponse response) {
        var handler = responseHandlers.get(response.getClass());
        if (handler != null) {
            return handler.apply(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unsupported response type");
        }
    }
}