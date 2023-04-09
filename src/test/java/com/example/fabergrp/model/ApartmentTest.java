package com.example.fabergrp.model;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApartmentTest {

    Apartment apartment;

    @BeforeEach
    void setup() {
        apartment = new Apartment();
    }

    @Test
    void testAllotWaterToTheApartment() {
        apartment.allotWaterToTheApartment(ApartmentType.TWO_BHK, 1, 3);
        assertTrue(apartment.isWaterAllotted);
    }

    @Test
    void testAllotWaterToTheApartmentAgain() {
        apartment.isWaterAllotted = true;
        assertThrows(WaterBillGenerationException.class, () -> apartment.allotWaterToTheApartment(ApartmentType.TWO_BHK, 1, 3));
    }

    @Test
    void testAddGuests() {
        assertThrows(WaterBillGenerationException.class, () -> apartment.addGuests(4));
    }

}