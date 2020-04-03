package Lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> contents;
//    private final String className;

    public Box() {
//        className = T.class.getTypeName();
    }

    public void add(T obj) {
        contents.add(obj);
    }

    public void remove(T obj) {
        contents.remove(obj);
    }

    public float getWeight() {
        float totalWeight = 0;
        for(T fr: contents) {
            totalWeight += fr.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(getWeight() - anotherBox.getWeight()) < 0.0001f;
    }

}
