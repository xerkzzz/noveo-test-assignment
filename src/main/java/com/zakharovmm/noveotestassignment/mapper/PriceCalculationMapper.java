package com.zakharovmm.noveotestassignment.mapper;

import com.zakharovmm.noveotestassignment.dto.PriceCalculationRequestDto;
import com.zakharovmm.noveotestassignment.model.PriceCalculationRequest;
import com.zakharovmm.noveotestassignment.model.PurchaseRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.ap.internal.gem.MappingConstantsGem.ComponentModelGem.SPRING;

@Mapper(componentModel = SPRING)
public interface PriceCalculationMapper {
    PriceCalculationRequest map(PriceCalculationRequestDto target);
    PriceCalculationRequest map(PurchaseRequest target);
}
