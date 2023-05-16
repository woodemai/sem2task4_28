package ru.vsu.cs.sem2task4_27;

public class Sort {

    public static <T> void sort(T[] sortData, int[] sortOrder) {
        if (sortData.length != sortOrder.length) return;
        for (int i = 1; i < sortData.length; i++) {
            int value = sortOrder[i];
            int j;
            for (j = i - 1; j >= 0 && sortOrder[j] > value; j--) {
                int temp = sortOrder[j + 1];
                sortOrder[j + 1] = sortOrder[j];
                sortOrder[j] = temp;

                T t = sortData[j + 1];
                sortData[j + 1] = sortData[j];
                sortData[j] = t;
            }
            sortOrder[j + 1] = value;
        }
    }
}
