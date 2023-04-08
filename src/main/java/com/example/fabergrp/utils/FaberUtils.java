package com.example.fabergrp.utils;

import java.io.IOException;

public class FaberUtils {

    public static String validateFilePathForTextFile(String path) throws IOException {
        if (path.endsWith(".txt") || path.endsWith(".text"))
            return path;
        else
            throw new IOException("Given file isn't a text file");
    }
}
