package com.zakharovmm.noveotestassignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String code;
    private float discount;
    private boolean isPercentage;
}
