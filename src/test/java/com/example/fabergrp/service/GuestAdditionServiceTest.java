package com.example.fabergrp.service;

import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestAdditionServiceTest {

    Apartment apartment;
    GuestAdditionService guestAdditionService = new GuestAdditionService();

    @BeforeEach
    void setup() {
        apartment = new Apartment();
        apartment.isWaterAllotted = true;
    }

    @Test
    void testExecuteOperationForException() {
        apartment.isWaterAllotted = false;
        assertThrows(WaterBillGenerationException.class, () -> guestAdditionService.executeOperation(null, apartment));
    }

    @Test
    void testExecuteOperation() {
        apartment.addGuests(10);
        List<String> args = new ArrayList<>(Collections.singletonList("10"));
        assertNull(guestAdditionService.executeOperation(args, apartment));
        assertEquals(20, apartment.getTotalGuests());
    }

    @Test
    void testExecuteOperation1() {
        List<String> args = new ArrayList<>(Collections.singletonList("-1"));
        assertNull(guestAdditionService.executeOperation(args, apartment));
        assertEquals(0, apartment.getTotalGuests());
    }

    @Test
    void testExecuteOperationWithNoInput() {
        List<String> args = new ArrayList<>(Collections.emptyList());
        assertThrows(WaterBillGenerationException.class, () -> guestAdditionService.executeOperation(args, apartment));
    }

    @Test
    void testGetInstance() {
        assertEquals(GuestAdditionService.class, GuestAdditionService.getInstance().getClass());
    }
}