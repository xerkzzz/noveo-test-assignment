package com.zakharovmm.noveotestassignment.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "taxes")
public class Tax {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private float taxPercent;

    private String regexPattern;
}
