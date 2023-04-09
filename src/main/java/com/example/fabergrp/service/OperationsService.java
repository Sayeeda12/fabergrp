package com.example.fabergrp.service;

import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Apartment;

import java.io.IOException;
import java.util.List;

public interface OperationsService {
    String executeOperation(List<String> args, Apartment apartment) throws WaterBillGenerationException;
}
