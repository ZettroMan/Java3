package Lesson3;

import java.io.Serializable;

public class Cat implements Serializable {
    String name;
    int weight;
    int age;

    public Cat(String name, int weight, int age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Кот " +  name + ", вес - " + weight +
                " кг, возраст (лет) - " + age + " ";
    }
}
