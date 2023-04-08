package com.example.fabergrp.constants;

import com.example.fabergrp.service.GuestAdditionService;
import com.example.fabergrp.service.OperationsService;
import com.example.fabergrp.service.WaterAllotmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Command {
    ALLOT_WATER(2, WaterAllotmentService::getInstance),
    ADD_GUESTS(1, GuestAdditionService::getInstance),
    BILL(0);

    @NonNull
    private int noOfArgs;

    private Supplier<? extends OperationsService> service;

}
