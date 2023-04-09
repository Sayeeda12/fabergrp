package com.example.fabergrp.service;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WaterAllotmentService implements OperationsService {

    @Getter
    private static final WaterAllotmentService instance = new WaterAllotmentService();

    @Override
    public String executeOperation(List<String> args, Apartment apartment) {
        try {
            if (apartment.isWaterAllotted)
                throw new WaterBillGenerationException("You're trying to allocate water to the apartment more than once. " +
                        "Please check your commands");

            //If negative values are passed as ratios, throw error
            List<Integer> shares = Arrays.stream(args.get(1).split(":")).map(Integer::parseInt).filter(value -> value > 0).collect(Collectors.toList());
            if (shares.size() < 2) {
                throw new IOException("Given input for water allocation is invalid. Given args: " + args);
            }

            ApartmentType apartmentType = ApartmentType.determineApartmentType(args.get(0));
            apartment.allotWaterToTheApartment(apartmentType, shares.get(0), (shares.get(0) + shares.get(1)));
        } catch (Exception e) {
            throw new WaterBillGenerationException("Water allotment for the apartment failed. Error message: " + e.getMessage());
        }
        return null;
    }
}
