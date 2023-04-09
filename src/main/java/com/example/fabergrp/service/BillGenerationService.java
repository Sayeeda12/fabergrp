package com.example.fabergrp.service;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import lombok.Getter;

import java.util.List;
import java.util.StringJoiner;

public class BillGenerationService implements OperationsService {

    @Getter
    private static final BillGenerationService instance = new BillGenerationService();

    @Override
    public String executeOperation(List<String> args, Apartment apartment) {
        if (!apartment.isWaterAllotted)
            throw new WaterBillGenerationException("Cannot generate bill as the water isn't allocated to the apartment yet.");

        StringJoiner finalBill = new StringJoiner(" ");
        //Add total litres consumed to the bill
        finalBill.add(Long.toString(apartment.getTotalWaterConsumedInLitres()));

        //Calculate total cost by default based on ratio
        long defaultWaterCost = calculateDefaultWaterCostBasedOnRatio(apartment);

        //Calculate total cost for guests based on slabs
        long additionalGuests = apartment.getTotalGuests() - apartment.getApartmentType().getNoOfPeople();

        long additionalWaterCostForGuests = calculateAdditionalCostBasedOnSlabs(additionalGuests);

        finalBill.add(Long.toString(defaultWaterCost + additionalWaterCostForGuests));

        return finalBill.toString();
    }

    private long calculateDefaultWaterCostBasedOnRatio(Apartment apartment) {
        long totalWaterConsumedByDefault = ApartmentType.getTotalWaterConsumedBasedOnType(apartment.getApartmentType());
        long waterPerShare = Math.round(totalWaterConsumedByDefault/apartment.getTotalShare());
        long corporationWaterCost = waterPerShare * apartment.getCorporationShare();
        long borewellWaterCost = Math.round(1.5 * waterPerShare * (apartment.getTotalShare() - apartment.getCorporationShare()));
        return corporationWaterCost + borewellWaterCost;
    }

    private long calculateAdditionalCostBasedOnSlabs(long guests) {
        long totalLitres = guests * 10 * 30;
        if (totalLitres > 3000) {
            return (totalLitres - 3000) * 8 + (1500 * 5) + (1000 * 3) + (500 * 2);
        } else if (totalLitres > 1500) {
            return (totalLitres - 1500) * 5 + (1000 * 3) + (500 * 2);
        } else if (totalLitres > 500) {
            return (totalLitres - 500) * 3 + (500 * 2);
        } else {
            return totalLitres * 2;
        }
    }
}
