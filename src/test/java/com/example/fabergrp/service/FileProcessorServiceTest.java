package com.example.fabergrp.service;

import com.example.fabergrp.constants.Command;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.model.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileProcessorServiceTest {

    @BeforeEach
    void setup() {
        FileProcessorService.isBillBeingGenerated = false;
    }

    @Test
    void testReadFileAndGetCommands() throws IOException {
        String filePath = "test-files/test.txt";

        List<Operation> operationList = new ArrayList<>();
        operationList.add(new Operation(Command.ALLOT_WATER, Arrays.asList("3", "2:1")));
        operationList.add(new Operation(Command.ADD_GUESTS, Collections.singletonList("4")));
        operationList.add(new Operation(Command.ADD_GUESTS, Collections.singletonList("1")));
        operationList.add(new Operation(Command.BILL, Collections.emptyList()));

        List<Operation> result = FileProcessorService.readFileAndGetCommands(filePath);
        assertEquals(operationList.get(0).getCommand(), result.get(0).getCommand());
        assertEquals(operationList.get(0).getCommandArgs().get(0), result.get(0).getCommandArgs().get(0));
        assertEquals(operationList.get(0).getCommandArgs().get(1), result.get(0).getCommandArgs().get(1));
        assertEquals(operationList.get(1).getCommandArgs().get(0), result.get(1).getCommandArgs().get(0));
    }

    @Test
    void testReadFileAndGetCommandsWithInvalidInput() {
        String filePath = "test-files/test1.txt";
        assertThrows(WaterBillGenerationException.class, () -> FileProcessorService.readFileAndGetCommands(filePath));
    }

    @Test
    void testReadFileAndGetCommandsWithInvalidInputs1() {
        String filePath = "test-files/test2.txt";
        assertThrows(WaterBillGenerationException.class, () -> FileProcessorService.readFileAndGetCommands(filePath));
    }

    @Test
    void testReadFileAndGetCommandsWithInvalidInputs3() {
        String filePath = "test-files/invalidTests.txt";
        assertThrows(WaterBillGenerationException.class, () -> FileProcessorService.readFileAndGetCommands(filePath));
    }

    @Test
    void testReadFileAndGetCommandsWithInvalidInputs4() {
        FileProcessorService.isBillBeingGenerated = true;
        String filePath = "test-files/invalidTests.txt";
        assertThrows(WaterBillGenerationException.class, () -> FileProcessorService.readFileAndGetCommands(filePath));
    }

    @Test
    void testReadFileAndGetCommandsWithInvalidFile() {
        FileProcessorService.isBillBeingGenerated = true;
        String filePath = "test-files/invalidTestss.txt";
        assertThrows(WaterBillGenerationException.class, () -> FileProcessorService.readFileAndGetCommands(filePath));
    }
}