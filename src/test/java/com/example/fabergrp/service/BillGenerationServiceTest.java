package com.example.fabergrp.service;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillGenerationServiceTest {

    Apartment apartment;
    BillGenerationService billGenerationService = new BillGenerationService();

    @BeforeEach
    void setup() {
        apartment = new Apartment();
        apartment.allotWaterToTheApartment(ApartmentType.THREE_BHK, 1, 6);
    }

    @Test
    void testExecuteOperationForException() {
        apartment.isWaterAllotted = false;
        assertThrows(WaterBillGenerationException.class, () -> billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation() {
        assertEquals("1500 2125", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation1() {
        apartment.addGuests(10);
        assertEquals("4500 13625", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation5() {
        apartment.addGuests(100);
        assertEquals("31500 229625", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation2() {
        Apartment apartment = new Apartment();
        apartment.allotWaterToTheApartment(ApartmentType.TWO_BHK, 2, 3);
        assertEquals("900 1050", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation3() {
        Apartment apartment = new Apartment();
        apartment.allotWaterToTheApartment(ApartmentType.TWO_BHK, 1, 1);
        assertEquals("900 900", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation4() {
        Apartment apartment = new Apartment();
        apartment.allotWaterToTheApartment(ApartmentType.TWO_BHK, 0, 3);
        assertEquals("900 1350", billGenerationService.executeOperation(null, apartment));
    }

    @Test
    void testGetInstance() {
        assertEquals(BillGenerationService.class, BillGenerationService.getInstance().getClass());
    }
}