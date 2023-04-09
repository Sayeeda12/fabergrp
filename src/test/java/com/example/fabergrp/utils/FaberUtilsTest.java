package com.example.fabergrp.utils;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class FaberUtilsTest {

    @Test
    void testValidateFilePathForTextFile() throws IOException {
        String path = "/Users/starannumr/Documents/Personal Learning/Express-APIs/g.txt";
        assertEquals(path, FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForTextFile1() throws IOException {
        String path = "/Users/starannumr/Documents/Personal Learning/Express-APIs/g.text";
        assertEquals(path, FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForInvalidTextFile() throws IOException {
        String path = "/Users/starannumr/Documents/Personal Learning/Express-APIs/g.txts";
        assertThrows(IOException.class, () -> FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForInvalidTextFile1() throws IOException {
        String path = "/Users/starannumr/Documents/Personal Learning/Express-APIs";
        assertThrows(IOException.class, () -> FaberUtils.validateFilePathForTextFile(path));
    }
}
