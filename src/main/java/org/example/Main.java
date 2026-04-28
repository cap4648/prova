package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String fileName = "data_ora.txt";
        String formattedDateTime = getCurrentFormattedDateTime();
        
        try {
            writeToFile(fileName, "Data e ora attuali: " + formattedDateTime + System.lineSeparator());
            System.out.println("Data e ora scritte in " + fileName + ": " + formattedDateTime);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file: " + e.getMessage());
        }
    }

    public static String getCurrentFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static void writeToFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(content);
        }
    }
}
