package com.example.fabergrp.model;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import lombok.Getter;

public class Apartment {
    public boolean isWaterAllotted = false;
    @Getter private int corporationShare = 0;
    @Getter private int totalShare = 0;
    @Getter private long totalGuests;
    @Getter ApartmentType apartmentType;
    int totalNoOfDaysInAMonth = 30;
    int waterInLitresPerPerson = 10;

    public void allotWaterToTheApartment(ApartmentType apartmentType, int corporationShare, int totalShare, long totalGuests) {
        this.corporationShare = corporationShare;
        this.totalShare = totalShare;
        this.totalGuests = totalGuests;
        this.apartmentType = apartmentType;
        isWaterAllotted = true;
    }

    public void addGuests(int noOfGuestsToBeAdded) {
        if(!isWaterAllotted)
            throw new WaterBillGenerationException("Error: Water isn't allocated to the apartment yet.");

        this.totalGuests += noOfGuestsToBeAdded;
    }

    public long getTotalWaterConsumedInLitres() {
        return this.totalGuests * totalNoOfDaysInAMonth * waterInLitresPerPerson;
    }
}
