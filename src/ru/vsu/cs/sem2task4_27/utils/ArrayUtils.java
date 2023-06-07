package ru.vsu.cs.sem2task4_27.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ArrayUtils {
    private ArrayUtils() {
    }

    private static final Random RND = new Random();


    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return new int[0];
        }
        return Arrays.stream(arr).mapToInt(i -> i).toArray();
    }

    public static double[] toPrimitive(Double[] arr) {
        if (arr == null) {
            return new double[0];
        }
        return Arrays.stream(arr).mapToDouble(i -> i).toArray();
    }

    public static char[] toPrimitive(Character[] arr) {
        if (arr == null) {
            return new char[0];
        }
        char[] result = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }

        return result;
    }

    public static boolean[] toPrimitive(Boolean[] arr) {
        if (arr == null) {
            return new boolean[0];
        }
        boolean[] result = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }

    public static Integer[] toObject(int[] arr) {
        if (arr == null) {
            return new Integer[0];
        }
        return (Integer[]) Arrays.stream(arr).mapToObj(i -> i).toArray();
    }

    public static Double[] toObject(double[] arr) {
        if (arr == null) {
            return new Double[0];
        }
        return (Double[]) Arrays.stream(arr).mapToObj(i -> i).toArray();
    }

    public static Character[] toObject(char[] arr) {
        if (arr == null) {
            return new Character[0];
        }
        Character[] result = new Character[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая упаковка в объект
            result[i] = arr[i];
        }
        return result;
    }

    public static Boolean[] toObject(boolean[] arr) {
        if (arr == null) {
            return new Boolean[0];
        }
        Boolean[] result = new Boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая упаковка в объект
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] toIntArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        Integer[] arr = list.toArray(new Integer[0]);
        scanner.close();
        return ArrayUtils.toPrimitive(arr);
    }

    public static double[] toDoubleArray(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useLocale(Locale.ROOT);
        scanner.useDelimiter("(\\s|[,;])+");
        List<Double> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextDouble());
        }
        Double[] arr = list.toArray(new Double[0]);
        scanner.close();
        return ArrayUtils.toPrimitive(arr);
    }

    public static String toString(int[] arr, String itemFormat) {
        if (arr == null) {
            return null;
        }
        if (itemFormat == null || itemFormat.length() == 0) {
            itemFormat = "%s";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.format(Locale.ROOT, itemFormat, arr[i]));
        }
        return sb.toString();
    }

    public static String toString(int[] arr) {
        return toString(arr, null);
    }

    public static String toString(double[] arr, String itemFormat) {
        if (arr == null) {
            return null;
        }
        if (itemFormat == null || itemFormat.length() == 0) {
            itemFormat = "%s";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.format(Locale.ROOT, itemFormat, arr[i]));
        }
        return sb.toString();
    }

    public static String toString(double[] arr) {
        return toString(arr, null);
    }



    public static int[][] toIntArray2(String[] lines) {
        int[][] arr2 = new int[lines.length][];
        for (int r = 0; r < lines.length; r++) {
            arr2[r] = toIntArray(lines[r]);
        }
        return arr2;
    }

    public static double[][] toDoubleArray2(String[] lines) {
        double[][] arr2 = new double[lines.length][];
        for (int r = 0; r < lines.length; r++) {
            arr2[r] = toDoubleArray(lines[r]);
        }
        return arr2;
    }


    public static String toString(int[][] arr2, String itemFormat) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < arr2.length; r++) {
            if (r > 0) {
                sb.append(System.lineSeparator());
            }
            sb.append(toString(arr2[r], itemFormat));
        }
        return sb.toString();
    }

    public static String toString(int[][] arr2) {
        return toString(arr2, null);
    }

    public static String toString(double[][] arr2, String itemFormat) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < arr2.length; r++) {
            if (r > 0) {
                sb.append(System.lineSeparator());
            }
            sb.append(toString(arr2[r], itemFormat));
        }
        return sb.toString();
    }

    public static String toString(double[][] arr2) {
        return toString(arr2, null);
    }

    public static String[] readLinesFromFile(String fileName) throws IOException {
        List<String> lines;
        try (Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new ReadLinesException(e);
        }
    }

    public static int[] readIntArrayFromFile(String fileName) throws IOException {
        return toIntArray(readLinesFromFile(fileName)[0]);
    }

    public static double[] readDoubleArrayFromFile(String fileName) {
        try {
            return toDoubleArray(readLinesFromFile(fileName)[0]);
        } catch (IOException e) {
            throw new ReadLinesException(e);
        }
    }

    public static int[][] readIntArray2FromFile(String fileName) {
        try {
            return toIntArray2(readLinesFromFile(fileName));
        } catch (IOException e) {
            throw new ReadLinesException(e);
        }
    }

    public static double[][] readDoubleArray2FromFile(String fileName) {
        try {
            return toDoubleArray2(readLinesFromFile(fileName));
        } catch (IOException e) {
            throw new ReadLinesException(e);
        }
    }

    private static class ReadLinesException extends RuntimeException {
        public ReadLinesException(String ex) {
            super(ex);
        }

        public ReadLinesException(RuntimeException ex) {
            super(ex);
        }

        public ReadLinesException(IOException ex) {
            super(ex);
        }
    }

    public static void writeArrayToFile(String fileName, int[] arr, String itemFormat)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(toString(arr, itemFormat));
        }
    }

    public static void writeArrayToFile(String fileName, int[] arr)
            throws FileNotFoundException {
        writeArrayToFile(fileName, arr, null);
    }

    public static void writeArrayToFile(String fileName, double[] arr, String itemFormat)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(toString(arr, itemFormat));
        }
    }

    public static void writeArrayToFile(String fileName, double[] arr)
            throws FileNotFoundException {
        writeArrayToFile(fileName, arr, null);
    }

    public static void writeArrayToFile(String fileName, int[][] arr2, String itemFormat)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(toString(arr2, itemFormat));
        }
    }

    public static void writeArrayToFile(String fileName, int[][] arr2)
            throws FileNotFoundException {
        writeArrayToFile(fileName, arr2, null);
    }

    public static void writeArrayToFile(String fileName, double[][] arr2, String itemFormat)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(toString(arr2, itemFormat));
        }
    }

    public static void writeArrayToFile(String fileName, double[][] arr2)
            throws FileNotFoundException {
        writeArrayToFile(fileName, arr2, null);
    }

    public static int[] createRandomIntArray(int length, int minValue, int maxValue) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++)
            arr[i] = minValue + RND.nextInt(maxValue - minValue);
        return arr;
    }

    public static int[] createRandomIntArray(int length, int maxValue) {
        return createRandomIntArray(length, 0, maxValue);
    }


    public static int[][] createRandomIntMatrix(int rowCount, int colCount, int minValue, int maxValue) {
        int[][] matrix = new int[rowCount][];
        for (int r = 0; r < rowCount; r++)
            matrix[r] = createRandomIntArray(colCount, minValue, maxValue);
        return matrix;
    }


    public static int[][] createRandomIntMatrix(int rowCount, int colCount, int maxValue) {
        return createRandomIntMatrix(rowCount, colCount, 0, maxValue);
    }
}
