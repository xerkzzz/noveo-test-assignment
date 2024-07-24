package com.zakharovmm.noveotestassignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;
}
