package Lesson6;

import java.util.Arrays;

public class ArrayTail {

    public static int[] getArrayTail(int[] array, int lastValue) {
        int index = array.length - 1;
        do {
            if (array[index] == lastValue) break;
        } while ((index--) > 0);

        if (index >= 0) {    //значение найдено
            if (index == array.length - 1) {
                return new int[0];
            }
            return Arrays.copyOfRange(array, index + 1, array.length);
        }
        throw new RuntimeException("Value is not found");
    }
}
