package com.example.fabergrp;

import com.example.fabergrp.model.Apartment;
import com.example.fabergrp.model.Operation;
import com.example.fabergrp.service.FileProcessorService;
import com.example.fabergrp.utils.FaberUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FaberGrpApplication {
    public static void main(String[] args) {
        try {
            //Read the file, validate it
            String filePath = FaberUtils.validateFilePathForTextFile(args[0]);

            //Fetch commands based on inputs in the text file, process the commands
            List<Operation> operationsToBePerformedList = FileProcessorService.readFileAndGetCommands(filePath);

            if(operationsToBePerformedList.isEmpty()) //Check for empty files
                throw new IOException("Please pass valid commands");

            Apartment apartment = new Apartment();

            //Process the commands only if Bill is generated in the end of the month, if not, ignore all commands
            if (operationsToBePerformedList.size() > 1 && FileProcessorService.isBillBeingGenerated) {
                List<String> result = operationsToBePerformedList.stream()
                        .map(
                                operation ->
                                        operation.getCommand()
                                                .getService()
                                                .get()
                                                .executeOperation(operation.getCommandArgs(), apartment))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                result.forEach(System.out::println);
            } else {
                throw new IOException("Either the bill is not being generated (HINT: Add BILL command) (or) the water isn't allocated to the apartment yet");
            }
        } catch (IOException exception) {
            System.out.println("Exception occurred while processing the file, error message is : \""+exception.getMessage() + "\"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
