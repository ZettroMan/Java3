package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println(Arrays.toString(array));
            exchange(array, 1, 3);
            System.out.println(Arrays.toString(array));
            exchange(array, 1, 5);
            System.out.println(Arrays.toString(array));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошла ошибка: выход за пределы массива");
        }

        ArrayList<Integer> integerArrayList = asArrayList(array);
        System.out.println(integerArrayList);
    }

    //меняет местами элементы массива array с индексами index1 и index2
    public static <T> void exchange(T[] array, int index1, int index2)
            throws ArrayIndexOutOfBoundsException {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //Изначально реализовал вот так ↓, тоже работало, но потом переписал на дженерик
//    public static void exchange(Object[] array, int index1, int index2)
//            throws ArrayIndexOutOfBoundsException {
//        Object temp = array[index1];
//        array[index1] = array[index2];
//        array[index2] = temp;
//    }

    //преобразовывает массив объектов в ArrayList
    public static <T> ArrayList<T>  asArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
 }
