package com.zarubov;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class CsvWriter {
    public void saveToCsv(String fileName, Double x, Double result) {
        String directory = "output\\";
        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = directory + fileName;

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(String.format(Locale.US, "%.5f, %.5f\n", x, result));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в " + filePath + ": " + e.getMessage());
        }
    }

    public void saveLogToCsv(String fileName, Double x,Double base, Double result) {
        String directory = "output\\";
        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = directory + fileName;

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(String.format(Locale.US, "%.5f, %.5f, %.5f\n", x,base, result));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в " + filePath + ": " + e.getMessage());
        }
    }
}
