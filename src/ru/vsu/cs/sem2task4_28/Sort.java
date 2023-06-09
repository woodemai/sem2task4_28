package ru.vsu.cs.sem2task4_28;

public class Sort {

    public static <T extends Comparable<? super T>> void sort(T[] sortData, int[] sortOrder) {
        for (int i = 0; i < sortData.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < sortData.length; ++j) {
                if (sortOrder[j] < sortOrder[minIndex]) {
                    minIndex = j;
                }
            }
            // обмен в массивы порядка
            int temp = sortOrder[minIndex];
            sortOrder[minIndex] = sortOrder[i];
            sortOrder[i] = temp;
            // обмен в массиве данных
            T t = sortData[minIndex];
            sortData[minIndex] = sortData[i];
            sortData[i] = t;
        }
    }
}
