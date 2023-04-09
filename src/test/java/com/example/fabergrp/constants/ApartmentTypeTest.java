package com.example.fabergrp.constants;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApartmentTypeTest {

    @Test
    void testDetermineApartmentType() throws IOException {
        assertEquals(ApartmentType.TWO_BHK, ApartmentType.determineApartmentType("2"));
    }

    @Test
    void testDetermineApartmentTypeWithException() {
        assertThrows(IOException.class, () -> ApartmentType.determineApartmentType("1"));
    }
}