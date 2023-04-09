package com.example.fabergrp.model;

import com.example.fabergrp.constants.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@AllArgsConstructor
public class Operation {
    @NonNull
    private Command command;

    @NonNull
    private List<String> commandArgs;
}
