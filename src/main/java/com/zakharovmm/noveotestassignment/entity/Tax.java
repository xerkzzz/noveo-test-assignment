package com.zakharovmm.noveotestassignment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
