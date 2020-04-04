package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println("Исходный массив: " + Arrays.toString(array));
            exchange(array, 1, 3);
            System.out.println("Массив после 1 перестановки: " + Arrays.toString(array));
            exchange(array, 2, 4);
            System.out.println("Массив после 2 перестановки: " + Arrays.toString(array));
            exchange(array, 1, 5);
            System.out.println("Массив после 3 перестановки: " + Arrays.toString(array));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошла ошибка: выход за пределы массива");
        }

        //Преобразовываем массив в ArrayList
        System.out.println("\nПреобразовываем массив в ArrayList");
        ArrayList<Integer> integerArrayList = getArrayList(array);
        System.out.println("Исходный массив: " + Arrays.toString(array));
        System.out.println("Получившийся список: " + integerArrayList);
        array[2] = 10; // изменили элемент массива
        System.out.println("Изменили элемент в массиве");
        System.out.println("Массив: " + Arrays.toString(array));
        System.out.println("На список не повлияло):");
        System.out.println("Список: " + integerArrayList);
        System.out.println();

        //Работаем с коробками и фруктами))
        Box<Fruit> fruitBox1 = new Box<>();    //универсальные коробки, в них можно положить
        Box<Fruit> fruitBox2 = new Box<>();    //фрукт любого типа (но не вперемешку)
        Box<Apple> appleBox1 = new Box<>();    //коробка для яблок
        Box<Orange> orangeBox1 = new Box<>();  //коробки для апельсинов
        Box<Orange> orangeBox2 = new Box<>();

        //добавляем фрукты в коробки
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple(2.0f));
        appleBox1.add(new Apple(1.5f));
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange(0.8f));
        orangeBox1.add(new Orange(1.3f));
        orangeBox1.add(new Orange());
        orangeBox1.add(new Orange());
        orangeBox2.add(new Orange(1.7f));
        orangeBox2.add(new Orange(1.8f));
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        fruitBox1.add(new Apple());    //в универсальные коробки можно положить фрукты любого типа
        fruitBox2.add(new Orange());
        fruitBox1.add(new Orange());  //тип не соответствует, ранее уже было добавлено яблоко
        System.out.println("-------------------------------------------------------------");
        showBoxInfo("appleBox1", appleBox1);
        showBoxInfo("orangeBox1", orangeBox1);
        showBoxInfo("orangeBox2", orangeBox2);
        showBoxInfo("fruitBox1", fruitBox1);
        showBoxInfo("fruitBox2", fruitBox2);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Сравниваем коробки appleBox1 и orangeBox1");
        System.out.println(appleBox1.compare(orangeBox1) ? "Коробки весят одинаково" : "Коробки отличаются по весу");
        System.out.println("Сравниваем коробки appleBox1 и orangeBox2");
        System.out.println(appleBox1.compare(orangeBox2) ? "Коробки весят одинаково" : "Коробки отличаются по весу");
        System.out.println("Сравниваем коробки orangeBox1 и fruitBox2");
        System.out.println(orangeBox1.compare(fruitBox2) ? "Коробки весят одинаково" : "Коробки отличаются по весу");
        System.out.println("-------------------------------------------------------------");

        showBoxInfo("orangeBox1", orangeBox1);
        showBoxInfo("appleBox1", appleBox1);
        System.out.println("Пересыпаем фрукты из orangeBox1 в appleBox1");
        appleBox1.relocateContents(orangeBox1);
        showBoxInfo("orangeBox1", orangeBox1);
        showBoxInfo("appleBox1", appleBox1);
        System.out.println("-------------------------------------------------------------");
        showBoxInfo("orangeBox1", orangeBox1);
        showBoxInfo("fruitBox1", fruitBox1);
        System.out.println("Пересыпаем фрукты из orangeBox1 в fruitBox1");
        fruitBox1.relocateContents(orangeBox1);
        showBoxInfo("orangeBox1", orangeBox1);
        showBoxInfo("fruitBox1", fruitBox1);
        System.out.println("-------------------------------------------------------------");
        showBoxInfo("fruitBox2", fruitBox2);
        showBoxInfo("orangeBox1", orangeBox1);
        System.out.println("Пересыпаем фрукты из orangeBox1 в fruitBox2");
        fruitBox2.relocateContents(orangeBox1);
        showBoxInfo("fruitBox2", fruitBox2);
        showBoxInfo("orangeBox1", orangeBox1);
        System.out.println("-------------------------------------------------------------");
        showBoxInfo("fruitBox1", fruitBox1);
        showBoxInfo("orangeBox2", orangeBox2);
        System.out.println("Пересыпаем фрукты из fruitBox1 в orangeBox2");
        orangeBox2.relocateContents(fruitBox1);
        showBoxInfo("fruitBox1", fruitBox1);
        showBoxInfo("orangeBox2", orangeBox2);
        System.out.println("-------------------------------------------------------------");
        showBoxInfo("appleBox1", appleBox1);
        showBoxInfo("fruitBox1", fruitBox1);
        System.out.println("Пересыпаем фрукты из fruitBox1 в appleBox1");
        appleBox1.relocateContents(fruitBox1);
        showBoxInfo("appleBox1", appleBox1);
        showBoxInfo("fruitBox1", fruitBox1);
        System.out.println("-------------------------------------------------------------");
        //а теперь кладем в пустую коробку из под яблок апельсин
        System.out.println("Кладем в пустую коробку из под яблок fruitBox1 апельсин");
        fruitBox1.add(new Orange());
        showBoxInfo("fruitBox1", fruitBox1);

    }

    //Изначально реализовал вот так ↓, тоже работало, но потом переписал на дженерик
//    public static void exchange(Object[] array, int index1, int index2)
//            throws ArrayIndexOutOfBoundsException {
//        Object temp = array[index1];
//        array[index1] = array[index2];
//        array[index2] = temp;
//    }

    // меняет местами элементы массива array с индексами index1 и index2
    public static <T> void exchange(T[] array, int index1, int index2)
            throws ArrayIndexOutOfBoundsException {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //преобразовывает массив объектов в ArrayList
    public static <T> ArrayList<T> getArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static void showBoxInfo(String boxName, Box<?> box) {
        if (box.getNumberOfItems() == 0) {
            System.out.println("Коробка " + boxName + " пуста");
            return;
        }
        System.out.println("Коробка " + boxName + ":  содержит " +
                box.getNumberOfItems() + " " + box.getContents().get(0).getName() + "(-а,-ов) общим весом " + box.getWeight());
    }
}
