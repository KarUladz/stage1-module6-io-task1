package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = null;
        int age = 0;
        String email = null;
        Long phone = null;

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                final String lineStringTrimmed = line.substring(line.indexOf(":") + 1).trim();
                if (line.startsWith("Name:")) {
                    name = lineStringTrimmed;
                } else if (line.startsWith("Age:")) {
                    age = Integer.parseInt(lineStringTrimmed);
                } else if (line.startsWith("Email:")) {
                    email = lineStringTrimmed;
                } else if (line.startsWith("Phone:")) {
                    phone = Long.parseLong(lineStringTrimmed);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read profile file", e);
        }

        return new Profile(name, age, email, phone);
    }
}
