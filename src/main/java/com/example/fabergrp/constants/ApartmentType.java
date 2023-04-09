package com.example.fabergrp.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

@Getter
@AllArgsConstructor
public enum ApartmentType {
    TWO_BHK(3),
    THREE_BHK(5);

    private int noOfPeople;

    public static ApartmentType determineApartmentType(String apartmentTypeInNumber) throws IOException {
        if(Integer.parseInt(apartmentTypeInNumber) == 2)
            return ApartmentType.TWO_BHK;
        else if(Integer.parseInt(apartmentTypeInNumber) == 3)
            return ApartmentType.THREE_BHK;
        else
            throw new IOException("Invalid apartment type passed");
    }

    public static int getTotalWaterConsumedBasedOnType(ApartmentType apartmentType) {
        if(apartmentType.name().equals("TWO_BHK"))
            return 3 * 10 * 30;
        else
            return 5 * 10 * 30;
    }
}
