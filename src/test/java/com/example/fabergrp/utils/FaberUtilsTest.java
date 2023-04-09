package com.example.fabergrp.utils;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class FaberUtilsTest {

    @Test
    void testValidateFilePathForTextFile() throws IOException {
        String path = "test-files/test1.txt";
        assertEquals(path, FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForTextFile1() throws IOException {
        String path = "test-files/test3.text";
        assertEquals(path, FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForInvalidTextFile() {
        String path = "test1.txts";
        assertThrows(IOException.class, () -> FaberUtils.validateFilePathForTextFile(path));
    }

    @Test
    void testValidateFilePathForInvalidTextFile1() {
        String path = "test-files";
        assertThrows(IOException.class, () -> FaberUtils.validateFilePathForTextFile(path));
    }
}
