package ru.vsu.cs.sem2task4_27.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOUtils {
    private IOUtils() {
    }

    public static String[] getStringArrayFromFile(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine().trim());
        }
        return list.toArray(new String[0]);
    }
}
