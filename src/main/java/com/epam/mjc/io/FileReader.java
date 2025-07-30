package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String name = null;
        int age = 0;
        String email = null;
        Long phone = null;

        try (InputStream inputStream = new FileInputStream(file)) {
            int c = inputStream.read();
            StringBuilder sb = new StringBuilder();
            while (c != -1) {
                sb.append((char) c);
                if ((char) c == '\n') {
                    final String titleLineTrimmed = sb.substring(0, sb.indexOf(":")).trim();
                    final String infoLineTrimmed = sb.substring(sb.indexOf(":") + 1).trim();
                    switch (titleLineTrimmed) {
                        case "Name":
                            name = infoLineTrimmed;
                            break;
                        case "Age":
                            age = Integer.parseInt(infoLineTrimmed);
                            break;
                        case "Email":
                            email = infoLineTrimmed;
                            break;
                        case "Phone":
                            phone = Long.parseLong(infoLineTrimmed);
                            break;
                        default:
                            break;
                    }
                    sb.setLength(0);
                }
                c = inputStream.read();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read profile file", e);
        }
        return new Profile(name, age, email, phone);
    }
}
