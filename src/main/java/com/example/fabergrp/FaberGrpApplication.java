package com.example.fabergrp;

import com.example.fabergrp.model.Operation;
import com.example.fabergrp.service.FileProcessorService;
import com.example.fabergrp.utils.FaberUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FaberGrpApplication {
    public static void main(String[] args) {
        try {
            //Read the file, validate it
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter file path (Only raw text files will be processed. Please don't enclose path around double quotes):");
            String filePath = FaberUtils.validateFilePathForTextFile(scanner.nextLine());

            //Enhance later for URL path or local path

            //Fetch commands based on inputs in the text file, process the commands
            List<Operation> operationsToBePerformedList = FileProcessorService.readFileAndGetCommands(filePath);

            //Process the commands only if Bill is generated in the end of the month, if not, ignore all commands
            if (FileProcessorService.isBillBeingGenerated()) {
                operationsToBePerformedList.stream()
                        .map(
                                operation ->
                                        operation.getCommand()
                                                .getService()
                                                .get()
                                                .executeOperation(operation.getCommandArgs()));
            } else {
                throw new IOException("Bill is expected to be generated at the end of the month");
            }

        } catch (IOException exception) {
            System.out.println("Exception occurred while processing the file, error message is : \""+exception.getMessage() + "\"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
