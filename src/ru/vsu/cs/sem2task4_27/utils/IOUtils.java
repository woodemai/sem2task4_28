package ru.vsu.cs.sem2task4_27.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOUtils {
    private IOUtils() {
    }

    public static String[][] getStringArrayFromFile(String fileName) {
        String[][] matrix = null;

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int rows = 0;
            int cols = 0;

            // подсчитываем количество строк и столбцов в матрице
            while (scanner.hasNextLine()) {
                rows++;
                String line = scanner.nextLine();
                String[] elements = line.split(" ");
                cols = elements.length;
            }

            matrix = new String[rows][cols];
            scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(" ");
                System.arraycopy(elements, 0, matrix[i], 0, elements.length);
                i++;
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }
}
