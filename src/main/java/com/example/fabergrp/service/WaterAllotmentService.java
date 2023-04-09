package com.example.fabergrp.service;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import lombok.Getter;

import java.io.IOException;
import java.util.List;

public class WaterAllotmentService implements OperationsService {

    @Getter
    private static final WaterAllotmentService instance = new WaterAllotmentService();

    @Override
    public String executeOperation(List<String> args, Apartment apartment) throws WaterBillGenerationException {
        try {
            if (apartment.isWaterAllotted)
                throw new WaterBillGenerationException("You're trying to allocate water to the apartment more than once. " +
                        "Please check your commands");

            String[] shares = args.get(1).split(":");
            ApartmentType apartmentType = ApartmentType.determineApartmentType(args.get(0));
            apartment.allotWaterToTheApartment(apartmentType,
                                                Integer.parseInt(shares[0]),
                                                Integer.parseInt(shares[0]) + Integer.parseInt(shares[1]),
                                                apartmentType.getNoOfPeople());
        } catch (Exception e) {
            throw new WaterBillGenerationException("Water allotment for the apartment failed. Error message: " + e.getMessage());
        }
        return null;
    }
}
