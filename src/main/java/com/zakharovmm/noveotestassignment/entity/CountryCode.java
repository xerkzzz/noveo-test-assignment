package com.zakharovmm.noveotestassignment.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum CountryCode {
    DE("Germany"),
    IT("Italy"),
    GR("Greece"),
    FR("France");

    private final String countryName;

    CountryCode(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public static CountryCode fromCode(String code) {
        for (CountryCode countryCode : CountryCode.values()) {
            if (countryCode.name().equalsIgnoreCase(code)) {
                return countryCode;
            }
        }
        throw new IllegalArgumentException("Unknown country code: " + code);
    }

    public static CountryCode fromName(String name) {
        for (CountryCode countryCode : CountryCode.values()) {
            if (countryCode.getCountryName().equalsIgnoreCase(name)) {
                return countryCode;
            }
        }
        throw new IllegalArgumentException("Unknown country name: " + name);
    }

    public static List<String> getAllCountriesAndCodes() {
        return Arrays.stream(CountryCode.values())
                .map(countryCode -> countryCode.name() + " - " + countryCode.getCountryName())
                .toList();
    }

    public static boolean existsByCode(String code) {
        return Arrays.stream(CountryCode.values())
                .anyMatch(countryCode -> countryCode.name().equalsIgnoreCase(code.toUpperCase()));
    }
}
