package com.example.fabergrp.service;

import com.example.fabergrp.constants.Command;
import com.example.fabergrp.exceptions.WaterBillGenerationException;
import com.example.fabergrp.exceptions.InvalidCommandException;
import com.example.fabergrp.model.Operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessorService {

    public static boolean isBillBeingGenerated = false; //Check if the command BILL exists in the command set. If not, don't process any commands

    private FileProcessorService() {}

    /*
        This method reads the commands from the raw text file
    */
    public static List<Operation> readFileAndGetCommands(String filePath) throws IOException {
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)) //Make sure the resources are cleaned
        ) {
            //Prepare each command as an operation and collect into a list
            return bufferedReader.lines()
                    .filter(Objects::nonNull)
                    .filter(line -> !line.isEmpty()) //Filter out empty lines in the file
                    .map(FileProcessorService::prepareCommand)
                    .collect(Collectors.toList());

        } catch (IOException exception) {
            throw new WaterBillGenerationException("Exception occurred while processing the file, error message is : \""+exception.getMessage() + "\"");
        } catch (InvalidCommandException exception) {
            throw new WaterBillGenerationException(exception.getMessage());
        }
    }

    private static Operation prepareCommand(String commandFromFile) throws InvalidCommandException {
      String[] paramsInCommand = commandFromFile.split(" ");
      try {
          if(!isBillBeingGenerated) { //Process commands only until BILL command, everything after that command will be ignored
              Command command = Command.valueOf(paramsInCommand[0]);

              if (command.equals(Command.BILL))
                  isBillBeingGenerated = true;

              int actualNoOfArgs = paramsInCommand.length - 1;
              if (command.getArgsCount() != actualNoOfArgs)
                  throw new IOException("The number of parameters don't match the requirement of the command! Got " + actualNoOfArgs + ", required: " + command.getArgsCount());

              List<String> commandArgs = Arrays.stream(paramsInCommand).skip(1).collect(Collectors.toList());

              return new Operation(command, commandArgs);
          } else {
              throw new IOException("No operations are permitted after BILL is generated. Please alter your command set.");
          }
      } catch (Exception e) {
          throw new InvalidCommandException("The given command is invalid. Error message: " + e.getMessage());
      }
    }
}
