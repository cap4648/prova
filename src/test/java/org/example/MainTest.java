package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainTest {

    @Test
    void testGetCurrentFormattedDateTime() {
        String dateTime = Main.getCurrentFormattedDateTime();
        assertNotNull(dateTime, "La data e ora formattate non dovrebbero essere null");
        // Esempio di formato: 2023-10-27 10:30:00
        assertTrue(dateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"),
                   "Il formato della data e ora non corrisponde al pattern atteso");
    }

    @Test
    void testWriteToFile() throws IOException {
        String testFileName = "test_data_ora.txt";
        String content = "Contenuto di test per il file.";
        
        // Assicurati che il file non esista prima del test
        Files.deleteIfExists(Paths.get(testFileName));

        Main.writeToFile(testFileName, content);

        File file = new File(testFileName);
        assertTrue(file.exists(), "Il file dovrebbe essere stato creato");

        String fileContent = Files.readString(Paths.get(testFileName));
        assertTrue(fileContent.contains(content), "Il file dovrebbe contenere il contenuto scritto");

        // Pulisci dopo il test
        Files.deleteIfExists(Paths.get(testFileName));
    }
}
