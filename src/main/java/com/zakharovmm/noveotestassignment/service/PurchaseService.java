package com.zakharovmm.noveotestassignment.service;

import com.zakharovmm.noveotestassignment.dto.PurchaseResponse;
import com.zakharovmm.noveotestassignment.model.PurchaseRequest;

public interface PurchaseService {
    PurchaseResponse purchase(PurchaseRequest request);

}
