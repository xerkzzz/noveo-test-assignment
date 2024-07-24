package com.zakharovmm.noveotestassignment.mapper;


import com.zakharovmm.noveotestassignment.dto.PurchaseRequestDto;
import com.zakharovmm.noveotestassignment.model.PaymentProcessorType;
import com.zakharovmm.noveotestassignment.model.PurchaseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface PurchaseMapper {

    @Mapping(source = "paymentProcessor", target = "paymentProcessor", qualifiedByName = "stringToPaymentProcessorType")
    PurchaseRequest map(PurchaseRequestDto target);


    @Named("stringToPaymentProcessorType")
    static PaymentProcessorType mapStringToPaymentProcessorType(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Payment processor cannot be null or empty");
        }
        return PaymentProcessorType.fromValue(value);
    }

}
