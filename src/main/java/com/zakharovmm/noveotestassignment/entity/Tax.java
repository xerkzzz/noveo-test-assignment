package com.zakharovmm.noveotestassignment.entity;


import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "taxes")
public class Tax {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private float taxPercent;

    @Enumerated(STRING)
    private CountryCode countryCode;

    private String regexPattern;
}
