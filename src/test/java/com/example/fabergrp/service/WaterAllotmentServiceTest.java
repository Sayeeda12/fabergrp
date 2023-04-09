package com.example.fabergrp.service;

import com.example.fabergrp.constants.ApartmentType;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaterAllotmentServiceTest {

    WaterAllotmentService waterAllotmentService = new WaterAllotmentService();
    Apartment apartment;

    @BeforeEach
    void setup() {
        apartment = new Apartment();
    }

    @Test
    void testExecuteOperationForException() {
        apartment.isWaterAllotted = true;
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation() {
        List<String> args = new ArrayList<>(Arrays.asList("3", "1:5"));
        assertNull(waterAllotmentService.executeOperation(args, apartment));
        assertTrue(apartment.isWaterAllotted);
        assertEquals(ApartmentType.THREE_BHK, apartment.getApartmentType());
        assertEquals(apartment.getCorporationShare(), 1);
        assertEquals(apartment.getTotalShare(), 6);
        assertEquals(apartment.getTotalGuests(), 5);
    }

    @Test
    void testExecuteOperationForInvalidInput() {
        List<String> args = new ArrayList<>(Arrays.asList("3", "15"));
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(args, apartment));
    }

    @Test
    void testExecuteOperationForInvalidInput1() {
        List<String> args = new ArrayList<>(Arrays.asList("5", "1:5"));
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(args, apartment));
    }

    @Test
    void testExecuteOperationForInvalidInput2() {
        List<String> args = new ArrayList<>(Arrays.asList("3", "1:"));
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(args, apartment));
    }

    @Test
    void testExecuteOperationForInvalidInput3() {
        List<String> args = new ArrayList<>(Arrays.asList("3", "0:0"));
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(args, apartment));
    }

    @Test
    void testExecuteOperationForInvalidInput4() {
        List<String> args = new ArrayList<>(Arrays.asList("3", "-1:-1"));
        assertThrows(WaterBillGenerationException.class, () -> waterAllotmentService.executeOperation(args, apartment));
    }

    @Test
    void testGetInstance() {
        assertEquals(WaterAllotmentService.class, WaterAllotmentService.getInstance().getClass());
    }
}