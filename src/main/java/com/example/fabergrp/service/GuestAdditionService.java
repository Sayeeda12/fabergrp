package com.example.fabergrp.service;

import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import lombok.Getter;

import java.util.List;

public class GuestAdditionService implements OperationsService {

    @Getter
    private static final GuestAdditionService instance = new GuestAdditionService();

    @Override
    public String executeOperation(List<String> args, Apartment apartment) {

        if (!apartment.isWaterAllotted)
            throw new WaterBillGenerationException("Error: Cannot add guests as water isn't allocated to the apartment yet.");

        try {
            int noOfGuestsToBeAdded = Math.max(Integer.parseInt(args.get(0)), 0); //If negative numbers are passed, it's considered as 0
            apartment.addGuests(noOfGuestsToBeAdded);
            return null;
        } catch (Exception e) {
            throw new WaterBillGenerationException("Error occurred while adding guests. Error message: " + e.getMessage());
        }
    }
}
