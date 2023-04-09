package com.example.fabergrp.constants;

import com.example.fabergrp.service.BillGenerationService;
import com.example.fabergrp.service.GuestAdditionService;
import com.example.fabergrp.service.OperationsService;
import com.example.fabergrp.service.WaterAllotmentService;
import lombok.*;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter(AccessLevel.PUBLIC)
public enum Command {
    ALLOT_WATER(2, WaterAllotmentService::getInstance),
    ADD_GUESTS(1, GuestAdditionService::getInstance),
    BILL(0, BillGenerationService::getInstance);

    private int argsCount;

    private Supplier<? extends OperationsService> service;

}
