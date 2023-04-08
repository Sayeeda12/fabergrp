package com.example.fabergrp.service;

import lombok.Getter;

import java.util.List;

public class GuestAdditionService implements OperationsService {

    @Getter
    private static final WaterAllotmentService instance = new WaterAllotmentService();

    @Override
    public String executeOperation(List<String> commands) {
        return null;
    }
}
