package com.example.fabergrp.model;

import com.example.fabergrp.constants.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Operation {
    private Command command;

    private List<String> commandArgs;
}
