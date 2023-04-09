package com.example.fabergrp.constants;

import com.example.fabergrp.service.BillGenerationService;
import com.example.fabergrp.service.GuestAdditionService;
import com.example.fabergrp.service.WaterAllotmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void testGetService() {
        assertEquals(WaterAllotmentService.class, Command.ALLOT_WATER.getService().get().getClass());
    }

    @Test
    void testGetService1() {
        assertEquals(GuestAdditionService.class, Command.ADD_GUESTS.getService().get().getClass());
    }

    @Test
    void testGetService2() {
        assertEquals(BillGenerationService.class, Command.BILL.getService().get().getClass());
    }

}